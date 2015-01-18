package com.lhx.testserach;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

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

public class TestFuzzyQuery {
    public static void main(String[] args) throws IOException {
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata" ;
        Directory dir = FSDirectory.getDirectory(indexDir);
        IndexSearcher searcher = new IndexSearcher(dir);
        ScoreDoc[] hits = null ;
        Term term = new Term("name", "zhangsan");
        FuzzyQuery query = new FuzzyQuery(term) ;
        TopDocs topDocs = searcher.search(query, 3) ;
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
        dir.close();
    }

}
