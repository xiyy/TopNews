# TopNews
一款Android新闻客户端，独立开发完成，主要功能包括：

1 新闻频道分类，头条、社会、国内、娱乐、体育、军事、科技、财经、时尚
  使用ViewPager+FragmentPagerAdapter+Fragment+TabLayout完成布局
  使用HttpOK从聚合数据获取新闻数据
  使用Gson对新闻数据解析
  使用EventBus传递消息
  
2 新闻内容浏览
  使用RecyclerView完成新闻列表
  使用Glide完成新闻图片加载
  使用webView加载新闻地址URL，并可与js交互
  使用photoView+ViewPager完成新闻大图列表浏览，保存新闻图片到SdCard
  
3 阅读历史、我的收藏
  使用sqlite完成历史记录、收藏数据的管理工作
  使用ViewPager+FragmentPagerAdapter+Fragment完成布局，RecyclerView完成收藏/历史列表

4 直播功能，电视台直播
  使用GridView、ListView等完成直播列表布局，包括央视、卫视、地方、体育、影视、海外等频道
  直播源从现有直播平台利用fiddler抓包得到
  播放器使用vitamio框架，可小窗播放、可全屏播放

5 新闻分享微信、QQ、微博
  使用微信、QQ、微博等SDK完成新闻分享
 
6 用户登录，手机号码验证码登录、微信、QQ、微博登录
  手机号、验证码登录依赖LeanCloud SDK实现
  手机号、密码登录依赖LeanCloud SDK实现，以及数据存储
  微信、QQ、微博登录，利用SDK实现
  
7 用户反馈、我要爆料功能
  相机拍照选取图片并压缩
  完成图片拾取器，按文件夹对图片分类，从文件夹中选取图片，可同时选取多张、可预览
  数据发送到LeanCloud后台

8 定位+附近位置
  通过simple-location-tracker获取设备经纬度
  通过百度API接口获取用户附近位置，以列表形式呈现

9 系统设置
  编辑资料
  清除缓存
  检查版本
  非WiFi网络播放提醒



  

  
  
  
