/*
  $Id: GetStatsControlTest.java 3005 2014-07-02 14:20:47Z dfisher $

  Copyright (C) 2003-2014 Virginia Tech.
  All rights reserved.

  SEE LICENSE FOR MORE INFORMATION

  Author:  Middleware Services
  Email:   middleware@vt.edu
  Version: $Revision: 3005 $
  Updated: $Date: 2014-07-02 10:20:47 -0400 (Wed, 02 Jul 2014) $
*/
package org.ldaptive.ad.control;

import org.ldaptive.LdapUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for {@link GetStatsControl}.
 *
 * @author  Middleware Services
 * @version  $Revision: 3005 $ $Date: 2014-07-02 10:20:47 -0400 (Wed, 02 Jul 2014) $
 */
public class GetStatsControlTest
{


  /**
   * Get stats control test data.
   *
   * @return  response test data
   */
  @DataProvider(name = "response")
  public Object[][] createData()
  {
    final GetStatsControl ctrl = new GetStatsControl();
    ctrl.getStatistics().put("pagesReferenced", 45094);
    ctrl.getStatistics().put("index", "Ancestors_index:0:N;");
    ctrl.getStatistics().put("pagesRedirtied", 2);
    ctrl.getStatistics().put("entriesVisited", 5010);
    ctrl.getStatistics().put("logRecordCount", 0);
    ctrl.getStatistics().put("pagesDirtied", 0);
    ctrl.getStatistics().put("entriesReturned", 1);
    ctrl.getStatistics().put("callTime", 15);
    ctrl.getStatistics().put("logRecordBytes", 0);
    ctrl.getStatistics().put("threadCount", 1);
    ctrl.getStatistics().put("pagesPreread", 0);
    ctrl.getStatistics().put("pagesRead", 0);
    ctrl.getStatistics().put("filter", "(uid=2)");
    return
      new Object[][] {
        // BER:
        // 30:84:00:00:00:6E:02:01:01:02:01:01:02:01:03:02:01:0F:02:01:05:02:
        // 01:01:02:01:06:02:02:13:92:02:01:07:04:0A:20:28:75:69:64:3D:32:29:
        // 20:00:02:01:08:04:15:41:6E:63:65:73:74:6F:72:73:5F:69:6E:64:65:78:
        // 3A:30:3A:4E:3B:00:02:01:09:02:03:00:B0:26:02:01:0A:02:01:00:02:01:
        // 0B:02:01:00:02:01:0C:02:01:00:02:01:0D:02:01:02:02:01:0E:02:01:00:
        // 02:01:0F:02:01:00:
        new Object[] {
          LdapUtils.base64Decode(
            "MIQAAABuAgEBAgEBAgEDAgEPAgEFAgEBAgEGAgITkgIBBwQKICh1aWQ9MikgAAIB" +
            "CAQVQW5jZXN0b3JzX2luZGV4OjA6TjsAAgEJAgMAsCYCAQoCAQACAQsCAQACAQwC" +
            "AQACAQ0CAQICAQ4CAQACAQ8CAQA="),
          ctrl,
        },
      };
  }


  /**
   * @param  berValue  to encode.
   * @param  expected  get stats control to test.
   *
   * @throws  Exception  On test failure.
   */
  @Test(
    groups = {"control"},
    dataProvider = "response"
  )
  public void decode(final byte[] berValue, final GetStatsControl expected)
    throws Exception
  {
    final GetStatsControl actual = new GetStatsControl(
      expected.getCriticality());
    actual.decode(berValue);
    Assert.assertEquals(actual, expected);
  }
}