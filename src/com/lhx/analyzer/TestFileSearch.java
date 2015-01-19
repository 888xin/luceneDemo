package com.lhx.analyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-19 下午10:39
 *
 * @project luceneDemo
 * @package com.lhx.analyzer
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestFileSearch {

    public static void main(String[] args) throws IOException {
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata" ;
        Directory dir = FSDirectory.getDirectory(indexDir);
        IndexSearcher searcher = new IndexSearcher(dir);
        ScoreDoc[] hits = null ;
        TopDocCollector topDocCollector = new TopDocCollector(10);
        Term term = new Term("contents", "男子");
        TermQuery query =  new TermQuery(term);
        TopDocs topDocs = searcher.search(query,100);
        hits = topDocs.scoreDocs ;
        for (int i = 0; i < hits.length; i++) {
            Document doc = searcher.doc(hits[i].doc);
            System.out.print(hits[i].score + " ");
            System.out.print(doc.get("contents") + " ");
            System.out.println();
        }
        searcher.close();
        dir.close();
    }
}
