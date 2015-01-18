package com.lhx.testserach;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-18 下午3:40
 *
 * @project luceneDemo
 * @package com.lhx.testserach
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestBooleanQuery {
    public static void main(String[] args) throws IOException {
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata" ;
        Directory dir = FSDirectory.getDirectory(indexDir);
        IndexSearcher searcher = new IndexSearcher(dir);
        ScoreDoc[] hits = null ;
        Term term = new Term("name", "*g??n");
        WildcardQuery query = new WildcardQuery(term) ;
        Term term1 = new Term("address", "tianjin");
        TermQuery query1 = new TermQuery(term1);
        BooleanQuery query2 = new BooleanQuery();
        query2.add(query, BooleanClause.Occur.MUST);
        query2.add(query1, BooleanClause.Occur.MUST);
        TopDocs topDocs = searcher.search(query2, 3) ;
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
