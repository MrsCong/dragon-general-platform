package com.dgp.excel.autoconfig;

import com.alibaba.excel.converters.Converter;
import com.dgp.excel.enhancer.DefaultWriterBuilderEnhancer;
import com.dgp.excel.enhancer.WriterBuilderEnhancer;
import com.dgp.excel.handler.ExportExcelReturnValueHandler;
import com.dgp.excel.handler.ImageCellWriteHandler;
import com.dgp.excel.handler.ManySheetWriteHandler;
import com.dgp.excel.handler.SheetWriteHandler;
import com.dgp.excel.handler.SingleSheetWriteHandler;
import com.dgp.excel.head.I18nHeaderCellWriteHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ExcelHandlerConfiguration {

    private final ExcelConfigProperties configProperties;

    private final ObjectProvider<List<Converter<?>>> converterProvider;

    /**
     * ExcelBuild增强
     *
     * @return DefaultWriterBuilderEnhancer 默认什么也不做的增强器
     */
    @Bean
    @ConditionalOnMissingBean
    public WriterBuilderEnhancer writerBuilderEnhancer() {
        return new DefaultWriterBuilderEnhancer();
    }

    /**
     * 单sheet 写入处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public SingleSheetWriteHandler singleSheetWriteHandler() {
        return new SingleSheetWriteHandler(configProperties, converterProvider, writerBuilderEnhancer());
    }

    /**
     * 多sheet 写入处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public ManySheetWriteHandler manySheetWriteHandler() {
        return new ManySheetWriteHandler(configProperties, converterProvider, writerBuilderEnhancer());
    }

    /**
     * 返回Excel文件的 response 处理器
     *
     * @param sheetWriteHandlerList 页签写入处理器集合
     * @return ResponseExcelReturnValueHandler
     */
    @Bean
    @ConditionalOnMissingBean
    public ExportExcelReturnValueHandler responseExcelReturnValueHandler(
            List<SheetWriteHandler> sheetWriteHandlerList) {
        return new ExportExcelReturnValueHandler(sheetWriteHandlerList);
    }

    /**
     * excel 头的国际化处理器
     *
     * @param messageSource 国际化源
     */
    @Bean
    @ConditionalOnBean(MessageSource.class)
    @ConditionalOnMissingBean
    public I18nHeaderCellWriteHandler i18nHeaderCellWriteHandler(MessageSource messageSource) {
        return new I18nHeaderCellWriteHandler(messageSource);
    }

    /**
     * 多sheet 写入处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public ImageCellWriteHandler imageCellWriteHandler() {
        return new ImageCellWriteHandler();
    }

}
