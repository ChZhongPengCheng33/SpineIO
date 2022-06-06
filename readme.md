# Spine骨骼文件读写库（SpineIO）

## 概述

基于Java的Spine骨骼文件读写库，支持以下功能：

- 同版本骨骼文件的`JSON-二进制`格式互转，即
    - `*.skel`->`*.json`
    - `*.json`->`*.skel`
- 跨版本骨骼文件的`JSON-二进制`格式互转，即
    - `v3.5 *.skel`->`v3.6 *.json`
    - `v3.5 *.skel`->`v3.6 *.skel`
    - ......

## 功能开发进度

- [ ] `v3.5 skel -> json`，100%
- [ ] `v3.5 json -> skel`，0%
- [ ] `v3.6 skel -> json`，0%
- [ ] `v3.6 json -> skel`，0%
- [ ] ......

## 更新日志

- 2021年3月7日，星期日，Spine Version：v3.5
  - 初始化runtime分支，runtime分支不再依赖`Spring`；
  - 完成v3.5二进制骨骼文件的数据输入流（`DataInputStream`）的编写。
- 2022年2月17日，星期四，Spine Version：v3.5
  - 完成3.5.51.1版本的读取开发

## 合作

如有意合作开发此项目，请邮件联系：1565575613@qq.com或提交`pull request`。

当前项目贡献者：

- **危险的秦先生**
  - [B站](https://space.bilibili.com/53857152)
  - [Gitee](https://gitee.com/ZhongPengCheng)
- **凊弦凝绝Official**
  - [B站](https://space.bilibili.com/14435736/)
  - [GitHub](https://github.com/azurlane-doujin)