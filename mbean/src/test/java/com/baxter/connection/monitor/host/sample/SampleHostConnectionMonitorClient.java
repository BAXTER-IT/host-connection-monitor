/**
 * 
 */
package com.baxter.connection.monitor.host.sample;

import java.util.List;
import java.util.Timer;

import javax.management.Notification;
import javax.management.NotificationListener;

import com.baxter.connection.monitor.jmx.ConnectionMonitorClient;
import com.baxter.connection.monitor.jmx.ConnectionMonitorMXBean;
import com.baxter.connection.monitor.jmx.ConnectionStatusNotification;
import com.baxter.connection.monitor.jmx.JMXService;
import com.baxter.connection.monitor.jmx.RemoteJMXService;

/**
 * The sample Host Connection Monitor client application.
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public class SampleHostConnectionMonitorClient
{

  public static void main(final String[] args) throws Exception
  {
	final String jmxHost = "localhost";
	final int jmxPort = 1333;

	// Instantiate connection monitor client.
	// This is the main communication point for the client application.
	final JMXService jmxService = new RemoteJMXService(jmxHost, jmxPort);
	final ConnectionMonitorClient connectionMonitorClient = new ConnectionMonitorClient(jmxService);
	// List all the monitors registered on remote server
	final List<ConnectionMonitorMXBean> monitors = connectionMonitorClient.getConnectionMonitors();
	// Here we iterate over the individual monitors and subscribe for their status change notifications
	for (final ConnectionMonitorMXBean monitor : monitors)
	{
	  connectionMonitorClient.addNotificationListener(monitor.getMonitorType(), monitor.getConnectionName(),
		  new NotificationListener()
		  {
		    @Override
		    public void handleNotification(final Notification notification, final Object handback)
		    {
			  if (notification instanceof ConnectionStatusNotification)
			  {
			    final ConnectionStatusNotification csn = (ConnectionStatusNotification) notification;
			    System.out.printf("Received connection status notification from %1$s@%2$s: %3$s\n", monitor.getConnectionName(),
			        monitor.getMonitorType(), csn);
			  }
		    }
		  });
	}
	// Here is another example of notification listener to subscribe globally
	connectionMonitorClient.addNotificationListenerToAll(new NotificationListener()
	{
	  @Override
	  public void handleNotification(final Notification notification, final Object handback)
	  {
		if (notification instanceof ConnectionStatusNotification)
		{
		  final ConnectionStatusNotification csn = (ConnectionStatusNotification) notification;
		  System.out.printf("Received ConnectionNotification (for all): %1$s\n", csn);
		}
	  }
	});
	//wait for exit
	new Timer("wait for...");
  }

}
