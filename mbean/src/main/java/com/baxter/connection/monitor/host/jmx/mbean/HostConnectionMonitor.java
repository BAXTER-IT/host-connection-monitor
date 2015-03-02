/**
 * 
 */
package com.baxter.connection.monitor.host.jmx.mbean;

import java.util.Timer;
import java.util.TimerTask;

import com.baxter.connection.monitor.host.HostConnection;
import com.baxter.connection.monitor.host.HostStatus;
import com.baxter.connection.monitor.jmx.ConnectionMonitorMXBean;
import com.baxter.connection.monitor.jmx.mbean.AbstractConnectionMonitor;

/**
 * A JMX Bean for monitoring the Host Connection.
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public class HostConnectionMonitor extends AbstractConnectionMonitor<HostConnection> implements ConnectionMonitorMXBean
{

  /**
   * Milliseconds between the host connection status tests.
   */
  private final long pingInterval;

  /**
   * Last known host connection status.
   */
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

  /**
   * Start monitoring.
   */
  public void monitor()
  {
	final Timer timer = new Timer(String.format("HostConnectionMonitor-%1$s", getConnectionName()));
	timer.schedule(new CheckStatusTask(), 0, pingInterval);
  }

  /**
   * The timer task to be executed on monitoring timer.
   * @author yura
   */
  private class CheckStatusTask extends TimerTask
  {
	
	private CheckStatusTask()
	{
	  logger.trace("Instantiated monitoring task");
	}

	@Override
	public void run()
	{
	  logger.trace("Monitoring the {}", getConnection());
	  // Get real time status
	  final HostStatus newStatus = getConnection().getStatus();
	  if (lastStatus == null || !newStatus.equals(lastStatus))
	  { // The status has changed, fire a notification and save new status
		logger.debug("Monitoring status changed from {} to {}", lastStatus, newStatus);
		fireConnectionStatusChanged(newStatus);
		lastStatus = newStatus;
	  }
	}
	
  }

}
