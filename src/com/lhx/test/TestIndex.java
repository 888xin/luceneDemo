package com.lhx.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-18 下午12:50
 *
 * @project luceneDemo
 * @package com.lhx.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestIndex {

    public static void main(String[] args) throws IOException {
        String[] ids = {"1","2","3"};
        String[] names = {"zhangsan","lisi","wangwu"};
        String[] addresses = {"shanghai","beijing","guangzhou"};
        Analyzer analyzer = new StandardAnalyzer();
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata" ;
        Directory dir = FSDirectory.getDirectory(indexDir);
        //true表示创建或覆盖现有的索引；FALSE表示对当前索引进行追加
        //IndexWriter.MaxFieldLength.LIMITED 128byte
        IndexWriter writer = new IndexWriter(dir, analyzer, true, IndexWriter.MaxFieldLength.LIMITED);
        for (int i = 0; i < ids.length; i++) {
            Document document = new Document() ;
            document.add(new Field("id", ids[i], Field.Store.YES, Field.Index.ANALYZED));
            document.add(new Field("name", names[i], Field.Store.YES, Field.Index.NO));
            document.add(new Field("address", addresses[i], Field.Store.YES, Field.Index.ANALYZED));
            writer.addDocument(document);
        }
        writer.optimize();
        writer.close();
    }
}
