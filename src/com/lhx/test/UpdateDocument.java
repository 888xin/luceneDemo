package com.lhx.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by xin on 15-1-18 下午1:44
 *
 * @project luceneDemo
 * @package com.lhx.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class UpdateDocument {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata";
        Directory directory = FSDirectory.getDirectory(indexDir);
        IndexReader reader = IndexReader.open(directory);
        System.out.println("before delete : " + reader.numDocs() );
        reader.deleteDocuments(new Term("id","2"));
        System.out.println("after delete : " + reader.numDocs() );
        reader.close();
        IndexWriter writer = new IndexWriter(directory, analyzer, false, IndexWriter.MaxFieldLength.LIMITED);
        Document  document = new Document() ;
        document.add(new Field("id", "2", Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("name", "Tom", Field.Store.YES, Field.Index.NO));
        document.add(new Field("address", "tianjin", Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(document);
        writer.close();
        reader = IndexReader.open(directory);
        System.out.println("after add : " + reader.numDocs() );
        reader.close();
        directory.close();
    }
}
