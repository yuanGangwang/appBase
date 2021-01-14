# appBase
Android项目基类

# 导航栏快速接入方式

### 接入方式

```Java
maven {
url uri("http://nexus.tttinner.com:8888/nexus/content/repositories/releases/")
credentials {
username = "yuangang.wang"
password = "Abu0ln9vrD91e0m"
}
}
```

```

implementation 'com.app.base:utils:1.0.3'
//版本以及变动内容见最下方更新日志

```

## 使用方法


### 基础结构
[ (返回部分支持文字/图片) ---- 标题部分 ---- 右侧标题/菜单部分 ]

### 注意点

1）

所设置的高度为标题栏的高度，如果为沉浸式状态栏，会自动计算控件高度为

realHeight = 状态栏高度 + xml中设置的layout_height,

2）

Activity基础使用可以不用设置返回点击监听，因为在控件中判断了

```Java

if (listener != null) {
listener.onBackLayoutClick();
} else {
if ((getContext() instanceof Activity)) {
((Activity) getContext()).finish();
}


```

3) 对文字的fontFamily支持，可以配置相应的fontFamily 对应 蓝湖中的字体


#### 基础使用

```java
<com.example.appbase.naviga.NavigaView
android:layout_width="match_parent"
app:naviTitle="标题栏"
android:layout_height="44dp" />
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200522143520497.png)


----

#### 修改基础属性

```java
<com.example.appbase.naviga.NavigaView
android:layout_width="match_parent"
app:naviTitle="自定义背景/图片/文字颜色"
app:naviBackGround="@color/colorPrimary"
app:naviTitleColor="@color/colorWhite"
app:naviBackImg="@mipmap/ic_launcher"
android:layout_height="40dp" />
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200522144212961.png)
### 启用分割线 自定义线颜色 启用默认菜单icon
```java

<com.example.appbase.naviga.NavigaView
android:layout_width="match_parent"
app:naviTitle="按钮支持"
app:naviBackColor="@color/colorPrimary"
app:naviBackText="Back"
app:searchIcon="@mipmap/ic_launcher"
app:menuIcon="@mipmap/ic_setting_uncollapse"
android:layout_height="40dp" />

```

用代码标题修改基础属性

```Java
((NavigaView) findViewById(R.id.backNavi)).setOnBackClickListener(new NavigaView.OnApplyNaviListener() {
@Override
public void onBackImgClick() {
finish();
}

@Override
public void onApplyTitle(TextView title) {
//对标题做修改
}

@Override
public void onRightLayoutClick() {
//右侧的部分点击事件
}
});

//如不注册这个clickListener 也可以实现返回
```


#### 支持沉浸导航栏设置
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200522154134805.png)

1 设置沉浸 Acitivty中

StatusBarUtils.setTranslucentBar(getWindow());

2 xml中

```java
<com.example.appbase.naviga.NavigaView
android:layout_width="match_parent"
app:naviTitle="支持沉浸设置的导航栏"
app:isCoverStatus="true"
app:naviStatusColor="#78ef00"
android:layout_height="44dp" />
```


---


## 全部自定义属性：

```java
<declare-styleable name="NavigaView">

<!--导航背景颜色-->
<attr name="naviBackGround" format="color"/>
<!--是否覆盖状态栏-->
<attr name="isCoverStatus" format="boolean"/>
<!--状态栏颜色-->
<attr name="naviStatusColor" format="color" />

<!--标题-->
<attr name="naviTitle" format="string" />
<!--标题颜色-->
<attr name="naviTitleColor" format="color" />
<!--标题字体大小-->
<attr name="naviTitleSize" format="dimension" />
<!--标题字体设置-->
<attr name="naviFontFamily" format="string" />

<!--右侧标题-->
<attr name="naviRightTitle" format="string" />
<!--右侧标题颜色-->
<attr name="naviRightTitleColor" format="color" />
<!--右侧标题字体大小-->
<attr name="naviRightTitleSize" format="dimension" />
<!--右侧标题字体设置-->
<attr name="naviRightFontFamily" format="string" />

<!--返回图片-->
<attr name="naviBackImg" format="reference" />
<!--返回文字-->
<attr name="naviBackText" format="string" />
<attr name="naviBackColor" format="color"/>


<attr name="searchIcon" format="reference"/>
<attr name="menuIcon" format="reference"/>

<!--是否显示分割线图片-->
<attr name="hasNaviDivider" format="boolean" />
<!--分割线颜色-->
<attr name="naviDividerColor" format="color" />

</declare-styleable>
```


## 更新日志

1.0.0 基础功能完善

1.0.1 修复高度异常bug

1.0.2 新增标题 fontfamily 属性

1.0.3 修复在xml中使用自定义控件时候无法提示自定义属性问题(类名要与属性空间名相同)
新增右侧文字选项



### 网络请求封装目的
+ 统一网络请求操作，便于后续接入使用


### 接入
+ 将aar项目包放入module的libs文件夹
+ 在module的dependencies内增加
>implementation(name: 'net-1.0.0', ext: 'aar')


### **使用方式**
+ 初始化：  NetClient.getInstance().appKey(appkey);（此处appKey即为申请的app key）
+ 请求：
  
  JSON形式参数请求
  ```java
  NetClient.getInstance()
            .url(url)
            .jsonParams(jsonObject.toString())
            .headerMap(params)
            .callback(object : CallBackUtil() {
                override fun onFailure(throwable: Throwable?) {
                    addMsg("获取凭证失败" + throwable?.message)
                }

                override fun onResponse(response: com.google.gson.JsonObject?) {
                    addMsg("获取凭证成功" + response?.get("data")?.asString)
                    getToken(response?.get("data")?.asString)
                }
            })
            .post()
  ```
  Key-Value形式参数请求
  ```java
   NetClient.getInstance()
            .url(url2)
            .paramsMap(params)
            .headerMap(HeaderManager.makeHeader())
            .callback(object : CallBackUtil() {
                override fun onFailure(throwable: Throwable?) {
                    addMsg("获取用户授权码失败" + throwable?.message)
                }

                override fun onResponse(response: com.google.gson.JsonObject?) {
                    addMsg("获取用户授权码成功")
                    getUserInfo(Gson().fromJson(response, Token::class.java))
                }
            })
            .post()
  ```
  GET请求方式
  ```java
  NetClient.getInstance().url(Constants.getSPhoneArea)
                .headerMap(HeaderManager.makeHeader())
                .callback(new CallBackUtil() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        ToastUtil.getInstance(AreaSearchActivity.this).showCommon(throwable.getMessage());
                    }

                    @Override
                    public void onResponse(JsonObject response) {
                        areaResponse = new Gson().fromJson(response, AreaResponse.class);
                        generateTag(areaResponse.getData());
                    }

                })
                .get();
  ```

### 请求地址动态配置：
 + 现请求地址请统一配置在Constants类中；
 + 需要在项目根目录下的gradle.properties文件中指定
```gradle
DEBUG_BASE_URL=http://47.110.12.104:9000 //1
BASE_URL=http://api-gateway.globalneutron.com //2
```
1处极为debug环境下连接地址，2处为正式环境下连接地址



