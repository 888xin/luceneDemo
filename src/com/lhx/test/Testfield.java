package com.lhx.test;

import org.apache.lucene.document.Field;

/**
 * Created by xin on 15-1-18 下午12:07
 *
 * @project luceneDemo
 * @package com.lhx.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class Testfield {
    public static void main(String[] args) {
        Field field = new Field("fname", "fvalue", Field.Store.YES, Field.Index.ANALYZED) ;
    }
}
