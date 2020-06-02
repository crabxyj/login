package cn.edu.zucc.login.common.logs;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * @author crabxyj
 * @date 2020/6/1 13:54
 * mybatis sql日志记录
 */
@Slf4j
@Intercepts(value = {
        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
                        CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisSqlLogInterceptor implements Interceptor {
    private static final String LOG_TYPE = "mybatis-sql";

    private static final String SQL_LOG = " 用户：[{}]\t" +
            "log type = [{}]\t" +
            "sql = [{}]\t" +
            "params = [{}]\t" +
            "start time = [{}]\t" +
            "spend time = [{}]";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        MappedStatement ms = (MappedStatement) args[0];
        //当前SQL使用的是哪个Mapper,即哪个Mapper类

        //执行当前SQL的Mapper id,其组成 [ 类型.方法 ]
//        //获取当前执行的SQL使用哪个数据源,我这里的数据源组件使用的是Druid，如果使用c3p0或者其他,则需要查看相关API,一般来降一个项目可能会配多个数据源,但是数据源组件都会使用一个
//        //获取数据库的类型[即mysql,或者oracle等等]
        //存放的是SQL的参数[它是一个实例对象]
        Object parameterObject = args[1];

        BoundSql boundSql = ms.getBoundSql(parameterObject);
        //这个ParameterMapping表示当前SQL绑定的是哪些参数,及参数类型,但并不是参数本身

        //将参数值转成json字符串

        //要拦截的SQL,通过拦截器的SQL 其不带参数
        String srcSql = boundSql.getSql();
        //返回拼装好参数的SQL

        //先执行当前的SQL方法,即通过当前拦截器的CRUD操作,因为我们要返回这个结果
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        log.info(SQL_LOG,"user",LOG_TYPE,
//                srcSql,JSON.toJSONString(parameterObject),
//                format.format(start),
//                time);

//        UUID.randomUUID().toString()
        //记录SQL
        //入参
        //sql开始执行时间
        //sql执行结束时间
        //耗时
        //执行结果

        //返回拦截器拦截的执行结果
        return result;
    }

    /**
     * plugin方法是拦截器用于封装目标对象的，通过该方法我们可以返回目标对象本身，也可以返回一个它的代理。
     * 当返回的是代理的时候我们可以对其中的方法进行拦截来调用intercept方法，当然也可以调用其他方法
     * 对于plugin方法而言，其实Mybatis已经为我们提供了一个实现。Mybatis中有一个叫做Plugin的类，
     * 里面有一个静态方法wrap(Object target,Interceptor interceptor)，通过该方法可以决定要返回的对象是目标对象还是对应的代理。
     */
    @Override
    public Object plugin(Object o) {
//        只拦截Executor对象,减少目标被代理的次数
        if (o instanceof Executor) {
            return Plugin.wrap(o, this);
        }
        return o;
    }

    /**
     * setProperties方法是用于在Mybatis配置文件中指定一些属性的
     * 这个方法在Configuration初始化当前的Interceptor时就会执行
     */
    @Override
    public void setProperties(Properties properties) {

    }
//
//    /**
//     * @describe: 组装SQL
//     * @params:
//     * @Author: LiCheng
//     * @Date: 2019/7/4 15:10
//     */
//    public String formatSQL(String src, String dbType, String params) {
//        //要传入的SQLUtils的参数集合,实际上虽然泛型是Object,但其实都是基本数据类型
//        List<Object> paramList = new ArrayList();
//        //有了JSON字符串我们就可以通过正则表达式得到参数了
//        System.out.println(params);
//        //需要注意的是这个SQLUtils是Druid数据源中的一个工具类,因为有现成的拼sql的工具,所以我就不再重复造轮子了,如果你的项目并没有使用Druid,
//        //则需要将这个工具类加入到你的项目中
//        String retSQL = SQLUtils.format(src, dbType, paramList);
//        return retSQL;
//    }
}
