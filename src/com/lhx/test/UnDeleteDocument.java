package com.lhx.test;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-18 下午1:40
 *
 * @project luceneDemo
 * @package com.lhx.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class UnDeleteDocument {
    public static void main(String[] args) throws IOException {
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata";
        Directory directory = FSDirectory.getDirectory(indexDir);
        IndexReader reader = IndexReader.open(directory);
        System.out.println( reader.numDocs() );
        reader.close();
    }
}
