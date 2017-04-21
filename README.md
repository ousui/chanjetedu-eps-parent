CHANJETEDU-EPS-PARENT
Chanjet education Exam Paper Store Project
畅捷教育试题交易商店

==========
    
* chanjetedu-eps-api        主要的接口服务
* chanjetedu-eps-common     公共服务所在
* chanjetedu-eps-domain     与数据库对应的实体类
* chanjetedu-eps-songoku    试题交易商店的管理后台
* chanjetedu-eps-store      试题交易商店/前端构建

整个项目采用耦合度不是很高的策略，底层代码为 common domain，api store负责自己的实现，业务也写到这里。
