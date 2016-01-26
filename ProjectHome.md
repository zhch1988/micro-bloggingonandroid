# 基于新浪开放平台的Android微博客户端！ #
# 项目实践 #
# 常用工具在Download里面 #
# 项目的文档和项目周报在SVN里面 #

myeclipse6.0安装svn插件

1. 打开Myeclipse，在菜单栏中选择Help→Software Updates→Find and Install

2. 选择Search for new features to install，点击Next进入下一步；

3. 点击“New Archived Site”按钮，在弹出的对话框中找到：
> site-1.6.16.zip
> 点击OK，关闭对话框，并点击Finish按钮；

4. 进入安装画面。

5. 选择所要安装的SVN插件内容，这里去掉第最后选项里德二个选项Subclipse Integrations，点击下一步；

6. 选择 “I accept the terms in the license agreements”并点击Next，直到点击Finish即可，进入下一步。

7. 开始安装SVN插件，安装完成之后，重启Myeclipse。

8. 在菜单栏中选择Window→Open Perspective→Other打开Myeclipse试图列表。这个时候Myeclipse的视图列表中，就出现了“SVN Repository Exploring”一项。

9. 打开“SVN Repository Exploring”视图。在左边空白区域，单击右键→New→Repository Location。

10. 在Url一栏中输入https://micro-bloggingonandroid.googlecode.com/svn/trunk/， 点击Finish按钮。

11. 输入用户名和密码，点击OK即可看到SVN下的目录结构了。


12. MyEclipse环境应用
> 新建一项目Project1，在src目录新增一文件MyJsp.jsp（也可只是个空项目），Project1--->右键--->share project--->SVN--->使用已有的资源库位置--->https://micro-bloggingonandroid.googlecode.com/svn/trunk/
(上面建的)--->...--->finish，这是会切换到Team synchronizing透视图，Project1下的文件夹都带有个+号图标，右键--->提交，这样才将项目Project1提交到服务器，切换到SVN资源库研究透视图，刷新一下，就能看见添加的项目Project1了；在Project1右键--->检出为--->做为空间的项目检出--->....--->finish，这样便完成了项目从服务器的检出，这时你便可在MyEclipse Java Enterprise透视图下编辑修改项目了，编辑之后，Project1--->右键--->Team--->提交，完成项目在服务器端得更新，至此整个简单的使用流程完成。