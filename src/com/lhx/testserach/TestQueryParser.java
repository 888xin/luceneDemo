package com.lhx.testserach;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
/**
 * Created by xin on 15-1-18 下午4:36
 *
 * @project luceneDemo
 * @package com.lhx.testserach
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestQueryParser {
    public static void main(String[] args) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata";
        Directory dir = FSDirectory.getDirectory(indexDir);
        IndexSearcher searcher = new IndexSearcher(dir);
        ScoreDoc[] hits = null;
        QueryParser parser = new QueryParser("name", analyzer);
//        Query query = parser.parse("address:resides");
//        Query query = parser.parse("birthday:[19871212 TO 19881212]");
        Query query = parser.parse("zhansan~");
        TopDocCollector topdoc = new TopDocCollector(100);
        searcher.search(query, topdoc);
        hits = topdoc.topDocs().scoreDocs;
        for (int i = 0; i < hits.length; i++) {
            Document doc = searcher.doc(hits[i].doc);
            System.out.print(hits[i].score + " ");
            System.out.print(doc.get("id") + " ");
            System.out.print(doc.get("name") + " ");
            System.out.print(doc.get("address") + " ");
            System.out.println(doc.get("birthday") + " ");
        }
        searcher.close();
        dir.close();

    }
}
