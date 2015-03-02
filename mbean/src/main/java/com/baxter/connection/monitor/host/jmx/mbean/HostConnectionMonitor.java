/**
 * 
 */
package com.baxter.connection.monitor.host.jmx.mbean;

import com.baxter.connection.monitor.host.HostConnection;
import com.baxter.connection.monitor.host.HostStatus;
import com.baxter.connection.monitor.host.jmx.HostConnectionMonitorMXBean;
import com.baxter.connection.monitor.jmx.mbean.AbstractConnectionMonitor;

/**
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public class HostConnectionMonitor extends AbstractConnectionMonitor<HostConnection> implements HostConnectionMonitorMXBean
{

  private final long pingInterval;

  private HostStatus lastStatus;

  public HostConnectionMonitor(final HostConnection connection, final long pingInterval)
  {
	super(connection);
	this.pingInterval = pingInterval;
  }

  @Override
  public String getMonitorType()
  {
	return "host";
  }

  @Override
  public HostStatus getConnectionStatus()
  {
	return getConnection().getStatus();
  }

  /**
   * Start monitoring.
   */
  public void monitor()
  {
	// TODO Auto-generated method stub

  }

}
