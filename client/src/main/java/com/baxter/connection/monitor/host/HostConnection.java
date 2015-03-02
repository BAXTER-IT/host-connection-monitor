/**
 * 
 */
package com.baxter.connection.monitor.host;

import java.io.IOException;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxter.connection.monitor.Connection;

/**
 * @author xpdev
 * @sinceDevelopmentVersion
 */
public class HostConnection implements Connection
{
  private static final Logger LOGGER = LoggerFactory.getLogger(HostConnection.class);

  private final String hostName;

  /**
   * Milliseconds for ping timeout.
   */
  private final int pingTimeout;

  /**
   * 
   * @param hostName
   * @param pingTimeout in milliseconds
   */
  public HostConnection(final String hostName, final int pingTimeout)
  {
	this.hostName = hostName;
	this.pingTimeout = pingTimeout;
  }

  /**
   * {@inheritDoc}
   * @return the host name
   */
  @Override
  public String getName()
  {
	return this.hostName;
  }

  public long getPingTimeout()
  {
	return pingTimeout;
  }

  @Override
  public HostStatus getStatus()
  {
	try
	{
	  final InetAddress addr = InetAddress.getByName(hostName);
	  if (addr.isReachable(pingTimeout))
	  {
		return HostStatus.reachable;
	  }
	  else
	  {
		return HostStatus.unreachable;
	  }
	}
	catch (final IOException e)
	{
	  LOGGER.debug("Could not determine the status of {} host connection", hostName, e);
	  return HostStatus.unreachable; // TODO actually, we do not know, maybe just some network configuration or access problem etc
	}
  }

}
