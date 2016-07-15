package com.my.base;

// QueryOperators.java

/**
 *    版权(C)2010 10 gen . n:行情)。
*在Apache许可下的,2.0版本(“许可证”);
*你可能不使用这个文件除了遵守许可证。
*你可以获得许可证的副本
* http://www.apache.org/licenses/license - 2.0
*
*,除非适用法律要求或书面同意,软件
*在许可证下发布的diastributed“目前”的基础上,
*没有任何形式的保证或条件,明示或默示。
*查看许可证的管理权限和特定的语言
*限制下的许可。
 */

/**
 * MongoDB keywords for various query operations
 *
 * @author Julson Lim
 */
public class QueryOperators {
    public static final String OR = "$or";
    public static final String AND = "$and";

    public static final String GT = "$gt";
    public static final String GTE = "$gte";
    public static final String LT = "$lt";
    public static final String LTE = "$lte";

    public static final String NE = "$ne";
    public static final String IN = "$in";
    public static final String NIN = "$nin";
    public static final String MOD = "$mod";
    public static final String ALL = "$all";
    public static final String SIZE = "$size";
    public static final String EXISTS = "$exists";
    public static final String ELEM_MATCH = "$elemMatch";

    // (to be implemented in QueryBuilder)
    public static final String WHERE = "$where";
    public static final String NOR = "$nor";
    public static final String TYPE = "$type";
    public static final String NOT = "$not";

    // geo operators
    public static final String WITHIN = "$within";
    public static final String NEAR = "$near";
    public static final String NEAR_SPHERE = "$nearSphere";
    public static final String BOX = "$box";
    public static final String CENTER = "$center";
    public static final String POLYGON = "$polygon";
    public static final String CENTER_SPHERE = "$centerSphere";
    // (to be implemented in QueryBuilder)
    public static final String MAX_DISTANCE = "$maxDistance";
    public static final String UNIQUE_DOCS = "$uniqueDocs";


    // meta query operators (to be implemented in QueryBuilder)
    public static final String RETURN_KEY = "$returnKey";
    public static final String MAX_SCAN = "$maxScan";
    public static final String ORDER_BY = "$orderby";
    public static final String EXPLAIN =  "$explain";
    public static final String SNAPSHOT = "$snapshot";
    public static final String MIN = "$min";
    public static final String MAX = "$max";
    public static final String SHOW_DISK_LOC = "$showDiskLoc";
    public static final String HINT = "$hint";
    public static final String COMMENT = "$comment";
}
