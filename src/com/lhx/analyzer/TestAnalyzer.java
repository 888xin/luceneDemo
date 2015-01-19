package com.lhx.analyzer;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by xin on 15-1-19 下午8:26
 *
 * @project luceneDemo
 * @package com.lhx.analyzer
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestAnalyzer {

    public static void main(String[] args) throws IOException {
        //Analyzer analyzer = new StandardAnalyzer();
        //Analyzer analyzer = new SimpleAnalyzer();
        //Analyzer analyzer = new WhitespaceAnalyzer();
        //Analyzer analyzer = new ChineseAnalyzer() ;
        Analyzer analyzer = new CJKAnalyzer();

        //TokenStream tokenStream = analyzer.tokenStream("", new StringReader("welcome to use lucene!"));
        TokenStream tokenStream = analyzer.tokenStream("", new StringReader("我是中国人"));
        Token token = new Token();
        while (tokenStream.next(token) != null){
            System.out.println(token.term());
        }
    }
}
