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
