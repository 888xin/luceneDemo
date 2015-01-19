package com.lhx.analyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-19 下午10:11
 *
 * @project luceneDemo
 * @package com.lhx.analyzer
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestSearch {
    public static void main(String[] args) throws IOException {
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata" ;
        Directory dir = FSDirectory.getDirectory(indexDir);
        IndexSearcher searcher = new IndexSearcher(dir);
        ScoreDoc[] hits = null ;
        TopDocCollector topDocCollector = new TopDocCollector(10);
        Term term = new Term("address", "tianjin");
        TermQuery query = new TermQuery(term);
        searcher.search(query, topDocCollector) ;
        hits = topDocCollector.topDocs().scoreDocs ;
        for (int i = 0; i < hits.length; i++) {
            Document doc = searcher.doc(hits[i].doc);
            System.out.print(hits[i].score + " ");
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
