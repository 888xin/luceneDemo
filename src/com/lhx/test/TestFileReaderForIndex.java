package com.lhx.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by xin on 15-1-18 下午3:55
 *
 * @project luceneDemo
 * @package com.lhx.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestFileReaderForIndex {
    public static void main(String[] args) throws IOException {
        File file=new File("C:\\Users\\xin\\Desktop\\csdn\\lucenedata\\data.txt");
        FileReader fRead=new FileReader(file);
        char [] chs=new char [60000];
        fRead.read(chs);

        String strtemp=new String(chs);

        String [] strs=strtemp.split("Database: Compendex");

        System.out.println(strs.length);
        for(int i=0;i<strs.length;i++){
            strs[i]=strs[i].trim();

        }

        Analyzer analyzer=new StandardAnalyzer();
        String indexDir="C:\\Users\\xin\\Desktop\\csdn\\lucenedata";
        Directory dir= FSDirectory.getDirectory(indexDir);

        IndexWriter writer=new IndexWriter(dir,analyzer,false,IndexWriter.MaxFieldLength.UNLIMITED);

        for(int i=0;i<strs.length;i++){
            Document document=new Document();
            document.add(new Field("contents",strs[i],Field.Store.YES,Field.Index.ANALYZED));
            writer.addDocument(document);
        }
        writer.optimize();
        writer.close();
        dir.close();
        System.out.println("index ok!");
    }
}
