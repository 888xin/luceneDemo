package com.lhx.testserach;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
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

public class TestSerach {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata" ;
        Directory dir = FSDirectory.getDirectory(indexDir);
        IndexSearcher searcher = new IndexSearcher(dir);
        ScoreDoc[] hits = null ;
        //Term term = new Term("id", "2");
        Term term = new Term("address", "beijing");
        TermQuery query = new TermQuery(term);
        TopDocs topDocs = searcher.search(query, 4) ;
        hits = topDocs.scoreDocs ;
        for (int i = 0; i < hits.length; i++) {
            Document doc = searcher.doc(hits[i].doc);
            System.out.println( doc.get("id") );
            System.out.println( doc.get("name") );
            System.out.println( doc.get("address") );
            System.out.println( doc.get("birthday") );
        }
        searcher.close();
        dir.close();
    }

}
