/**
 * 
 */
package com.baxter.connection.monitor.host.sample;

import com.baxter.connection.monitor.host.HostConnection;
import com.baxter.connection.monitor.host.jmx.mbean.HostConnectionMonitor;

/**
 * The sample Host Connection Monitor server side application.
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public class SampleHostConnectionMonitorServer
{

  public static void main(String[] args)
  {
	String hostName = args[0];
	int pingTimeout = Integer.parseInt(args[1]);
	int pingInterval = Integer.parseInt(args[2]);

	// A server instantiates the host connection object
	final HostConnection connection = new HostConnection(hostName, pingTimeout);
	// Set up the monitor for connection
	final HostConnectionMonitor monitor = new HostConnectionMonitor(connection, pingInterval);
	// Register the mbean on server
	monitor.register();
	// Start monitoring the connection
	monitor.monitor();
  }

}
