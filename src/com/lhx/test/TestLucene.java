package com.lhx.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by xin on 15-1-18 上午11:37
 *
 * @project luceneDemo
 * @package com.lhx.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestLucene {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("", new StringReader("this is first lucene project"));
        Token token = new Token();
        while (tokenStream.next(token) != null){
            System.out.println(token.term());
        }
    }
}
