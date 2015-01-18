package com.lhx.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-18 下午2:14
 *
 * @project luceneDemo
 * @package com.lhx.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestRamDirectory {
    public static void main(String[] args) throws IOException {
        String[] ids = {"1","2","3"};
        String[] names = {"zhangsan","lisi","wangwu"};
        String[] addresses = {"shanghai","beijing","guangzhou"};
        Analyzer analyzer = new StandardAnalyzer();
        Directory dir = new RAMDirectory();
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
        writer.optimize();//索引段的合并
        writer.close();
        IndexReader reader = IndexReader.open(dir);
        System.out.println( reader.numDocs() );
        reader.close();
        dir.close();
    }
}
