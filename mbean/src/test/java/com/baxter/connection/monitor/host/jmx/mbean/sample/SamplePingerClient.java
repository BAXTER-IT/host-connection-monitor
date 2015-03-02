/**
 * 
 */
package com.baxter.connection.monitor.host.jmx.mbean.sample;

import java.io.IOException;
import java.util.List;
import java.util.Timer;

import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;

import com.baxter.connection.monitor.jmx.ConnectionMonitorClient;
import com.baxter.connection.monitor.jmx.ConnectionMonitorMXBean;
import com.baxter.connection.monitor.jmx.ConnectionNotification;

/**
 * Sample client for JMX connection monitor
 * @author Goszi, Bela
 *
 */
public class SamplePingerClient
{

  public static void main(String[] args) throws IOException, MalformedObjectNameException, InstanceNotFoundException
  {
	String jmxHost = args[0];
	int jmxPort = Integer.parseInt(args[1]);

	//initialize connection monitor client
	final ConnectionMonitorClient connectionMonitor = new ConnectionMonitorClient(jmxHost, jmxPort);
	//queries registered connections
	List<ConnectionMonitorMXBean> queryConnections = connectionMonitor.queryConnections();
	//print them out
	for (final ConnectionMonitorMXBean monitor : queryConnections)
	{
	  connectionMonitor.addNotificationListener(monitor.getMonitorType(), monitor.getConnectionName(), new NotificationListener()
	  {

		@Override
		public void handleNotification(Notification notification, Object handback)
		{
		  if (notification instanceof ConnectionNotification)
		  {
			ConnectionNotification cn = (ConnectionNotification) notification;

			System.out.printf("Received connection notification [%3$s ]from %1$s@%2$s\n", monitor.getConnectionName(),
			    monitor.getMonitorType(), cn.getConnection());
		  }
		}
	  });
	}
	//add connection listener
	connectionMonitor.addNotificationListenerToAll(new NotificationListener()
	{
	  @Override
	  public void handleNotification(Notification notification, Object handback)
	  {
		if (notification instanceof ConnectionNotification)
		{
		  ConnectionNotification cn = (ConnectionNotification) notification;
		  System.out.println("Received ConnectionNotification for all: " + cn.getConnection().toString());
		}
	  }
	});
	//wait for exit
	new Timer("wait for...");
  }
}
