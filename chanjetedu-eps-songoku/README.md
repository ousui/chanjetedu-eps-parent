chanjetedu-eps-songoku
======================

新项目采用 servlet 3.0 标准开发，所以是支持动态项目的，即无需 web.xml，将配置固化在代码当中。

入口类参考 `com.chanjet.edu.eps.songoku.config.WebApplicationLauncher`，其继承了 spring，这样 springmvc 就加载了进来，更加方便快捷。
