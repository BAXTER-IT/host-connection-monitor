/**
 * 
 */
package com.baxter.connection.monitor.host;

import com.baxter.connection.monitor.Status;

/**
 * Declares possible status values for Host Connection.
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public enum HostStatus implements Status
{

  /**
   * The target host is reachable from current process.
   */
  reachable,

  /**
   * The target host is unreachable.
   */
  unreachable

}
