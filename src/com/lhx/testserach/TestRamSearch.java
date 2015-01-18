package com.lhx.testserach;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-18 下午2:27
 *
 * @project luceneDemo
 * @package com.lhx.testserach
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestRamSearch {

    Directory dir = new RAMDirectory();

    public void createRamIndex() throws IOException {
        String[] ids = {"1","2","3"};
        String[] names = {"zhangsan","lisi","wangwu"};
        String[] addresses = {"shanghai","he resides in beijing","guangzhou"};
        Analyzer analyzer = new StandardAnalyzer();
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
    }

    public void searchRam() throws IOException {
        IndexSearcher searcher = new IndexSearcher(dir);
        ScoreDoc[] hits = null ;
        Term term = new Term("address", "beijing");
        PrefixQuery query = new PrefixQuery(term) ;
        TopDocs topDocs = searcher.search(query, 4) ;
        hits = topDocs.scoreDocs ;
        for (int i = 0; i < hits.length; i++) {
            Document doc = searcher.doc(hits[i].doc);
            System.out.print(doc.get("id") + " ");
            System.out.print(doc.get("name") + " ");
            System.out.print(doc.get("address") + " ");
            System.out.print(doc.get("birthday") + " ");
            System.out.println();
        }
        searcher.close();
    }

    public static void main(String[] args) throws IOException {
        TestRamSearch testRamSearch = new TestRamSearch() ;
        testRamSearch.createRamIndex();
        testRamSearch.searchRam();
    }

}
