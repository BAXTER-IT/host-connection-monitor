/**
 * 
 */
package com.baxter.connection.monitor.host.jmx.mbean.sample;

import com.baxter.connection.monitor.host.HostConnection;
import com.baxter.connection.monitor.host.jmx.mbean.HostConnectionMonitor;

/**
 * @author xpdev
 *
 */
public class SamplePingerServer
{

  public static void main(String[] args)
  {
	String hostName = args[0];
	int pingTimeout = Integer.parseInt(args[1]);
	int pingInterval = Integer.parseInt(args[2]);

	final HostConnection connection = new HostConnection(hostName, pingTimeout);
	final HostConnectionMonitor monitor = new HostConnectionMonitor(connection, pingInterval);
	monitor.register();
	monitor.monitor();
  }

}
