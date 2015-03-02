/**
 * 
 */
package com.baxter.connection.monitor.host.jmx;

import com.baxter.connection.monitor.host.HostStatus;
import com.baxter.connection.monitor.jmx.ConnectionMonitorMXBean;

/**
 * MXBean interface for Host Connection Monitor.
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public interface HostConnectionMonitorMXBean extends ConnectionMonitorMXBean
{

  HostStatus getConnectionStatus();

}
