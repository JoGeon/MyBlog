package com.appengine.myblog.util;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 14-2-21</p>
 * <p>Company: NO</p>
 * User: zhanglei
 * Date: 14-2-21
 * Time: 下午4:59
 */
public class ServletUtilTest extends TestCase {
    @Test
    public void testFormatDate() throws Exception {

        String articleUrl = "20131128";
        String articleDate = ServletUtil.parseURL(articleUrl);
        assertEquals(articleDate,"2013-11-28" );

        String articleUrl1 = "201311";
        String articleDate1 = ServletUtil.parseURL(articleUrl1);
        assertEquals(articleDate1,"2013-11" );

    }
}
