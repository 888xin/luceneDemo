package com.lhx.testserach;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-18 下午2:27
 *
 * @project luceneDemo
 * @package com.lhx.testserach
 * @Description 分页搜索
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestWildcardQuery3 {
    public static void main(String[] args) throws IOException {
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata" ;
        Directory dir = FSDirectory.getDirectory(indexDir);
        IndexSearcher searcher = new IndexSearcher(dir);
        ScoreDoc[] hits = null ;
        Term term = new Term("contents", "*onto*");
        WildcardQuery query = new WildcardQuery(term) ;
        TopDocs topDocs = searcher.search(query, 127) ;
        int eachPageNumber = 10 ; //每页显示的记录个数
        int pageNumber = 3 ; //页码数
        hits = topDocs.scoreDocs ;
        for (int i = (pageNumber-1) * eachPageNumber; i < pageNumber * eachPageNumber; i++) {
            Document doc = searcher.doc(hits[i].doc);
            System.out.println(doc.get("contents"));
        }
        searcher.close();
        dir.close();
    }

}
