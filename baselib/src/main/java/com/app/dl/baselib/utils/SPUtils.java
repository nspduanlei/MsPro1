package com.app.dl.baselib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class SPUtils {

  /**
   * =============================key====================================
   **/

  //是否第一次进入app true 是， false 不是
//  public static final String IS_FIRST_LAUNCH = "is_first_launch";

  public static final String FIRST_OPEN = "first_open";

  //是否有新的系统消息
  public static final String HAS_NEW_MESSAGE = "has_new_message";

  //用户选择不更新版本
  public static final String IS_NOT_UPDATE_VERSION = "select_not_update_version";

  //登录成功了未完善资料
  public static final String IS_COMPLETE = "select_not_update_version";

  //手机号
  public static final String PHONE = "user_phone";

  //用户名
  public static final String USER_NAME = "user_name";


  //用户编号
  public static final String USER_NO = "user_no";

  //用户登录token
  public static final String TOKEN = "token";

  //是否加密 1 不加密，2 加密   SPUtils.put(mContext, SPUtils.ENCRYPT, "2");
  public static final String ENCRYPT = "encrypt";

  //orgId
  public static final String ORG_ID = "org_id";

  //orgName
  public static final String ORG_NAME = "org_name";

  //职位等级
  public static final String POSITION_LEVEL = "position_level";

  //部门名称
  public static final String DEP_NAME = "dep_name";

  //用户头像
  public static final String USER_IMG = "user_img";
  public static final String NICK_NAME = "nick_name";

  //JPUSH id
  public static final String REGISTRATION_ID = "registration_id";

  //会员号
  public static final String ERP_NO = "erp_no";

  //用户类型
  public static final String USER_TYPE = "user_type";

  //购物车商品数量
  public static final String SHOP_CART_NUM = "shop_cart_num";
  public static final String CUSTOMER_ID = "customer_id";
  public static final String REMAIN_ACCOUNT = "remain_account";
  public static final String AUTH_STATUS = "auth_status";

  //开户状态
  public static final String SIGN_ACCOUNT_STATUS = "sign_account_status";
  //是否禁用
  public static final String STATUS = "status";

  //新消息提醒
  public static final String ORDER_HAS_NEW_MSG = "order_has_new_msg";
  public static final String COMPACT_HAS_NEW_MSG = "compact_has_new_msg";
  /**
   * =================================================================
   **/
  public SPUtils() {
        /* cannot be instantiated */
    throw new UnsupportedOperationException("cannot be instantiated");
  }

  /**
   * 保存在手机里面的文件名
   */
  public static final String FILE_NAME = "share_data";

  /**
   * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
   *
   * @param context
   * @param key
   * @param object
   */
  public static void put(Context context, String key, Object object) {
    if (object == null) {
      return;
    }

    SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
        Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();

    if (object instanceof String) {
      editor.putString(key, (String) object);
    } else if (object instanceof Integer) {
      editor.putInt(key, (Integer) object);
    } else if (object instanceof Boolean) {
      editor.putBoolean(key, (Boolean) object);
    } else if (object instanceof Float) {
      editor.putFloat(key, (Float) object);
    } else if (object instanceof Long) {
      editor.putLong(key, (Long) object);
    } else {
      editor.putString(key, object.toString());
    }

    SharedPreferencesCompat.apply(editor);
  }

  /**
   * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
   *
   * @param context
   * @param key
   * @param defaultObject
   * @return
   */
  public static Object get(Context context, String key, Object defaultObject) {
    if (context == null) {
      return null;
    }
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
        Context.MODE_PRIVATE);
    if (sp == null) {
      return null;
    }
    if (defaultObject instanceof String) {
      return sp.getString(key, (String) defaultObject);
    } else if (defaultObject instanceof Integer) {
      return sp.getInt(key, (Integer) defaultObject);
    } else if (defaultObject instanceof Boolean) {
      return sp.getBoolean(key, (Boolean) defaultObject);
    } else if (defaultObject instanceof Float) {
      return sp.getFloat(key, (Float) defaultObject);
    } else if (defaultObject instanceof Long) {
      return sp.getLong(key, (Long) defaultObject);
    }

    return null;
  }

  /**
   * 移除某个key值已经对应的值
   *
   * @param context
   * @param key
   */
  public static void remove(Context context, String key) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
        Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.remove(key);
    SharedPreferencesCompat.apply(editor);
  }

  /**
   * 清除所有数据
   *
   * @param context
   */
  public static void clear(Context context) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
        Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.clear();
    SharedPreferencesCompat.apply(editor);
  }

  /**
   * 查询某个key是否已经存在
   *
   * @param context
   * @param key
   * @return
   */
  public static boolean contains(Context context, String key) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
        Context.MODE_PRIVATE);
    return sp.contains(key);
  }

  /**
   * 返回所有的键值对
   *
   * @param context
   * @return
   */
  public static Map<String, ?> getAll(Context context) {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
        Context.MODE_PRIVATE);
    return sp.getAll();
  }

  /**
   * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
   *
   * @author zhy
   */
  private static class SharedPreferencesCompat {
    private static final Method sApplyMethod = findApplyMethod();

    /**
     * 反射查找apply的方法
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Method findApplyMethod() {
      try {
        Class clz = SharedPreferences.Editor.class;
        return clz.getMethod("apply");
      } catch (NoSuchMethodException e) {
      }

      return null;
    }

    /**
     * 如果找到则使用apply执行，否则使用commit
     *
     * @param editor
     */
    public static void apply(SharedPreferences.Editor editor) {
      try {
        if (sApplyMethod != null) {
          sApplyMethod.invoke(editor);
          return;
        }
      } catch (IllegalArgumentException e) {
      } catch (IllegalAccessException e) {
      } catch (InvocationTargetException e) {
      }
      editor.commit();
    }
  }

}