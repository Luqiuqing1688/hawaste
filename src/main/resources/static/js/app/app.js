new Vue({
    el: "#main-container",
    data: {
        pageInfo: {
            current: 1,
            size: 5
        },
        app: {
            platform: 0,
            forceUpdate: 0
        },
        active: false
    },
    methods: {
        select: function (pageNum, pageSize) {
            axios({
                url: '/manager/app/query',
                params: {
                    current: pageNum,
                    size: pageSize
                }
            }).then(response => {
                //第一个data：ResultBean<Page>,第二个data：Page对象
                this.pageInfo = response.data.data;
            }).catch(function (error) {
                console.log(error);
            })
        },
        save: function () {
            axios({
                url: '/manager/app/saveOrUpdate',
                method: 'post',
                data: this.app
            }).then(response => {
                if (response.data.code == 200) {
                    this.active = false; //切换列表的div
                    //更新数据列表
                    this.select(this.pageInfo.current, this.pageInfo.size);
                    this.app = {
                        platform: 0,
                        forceUpdate: 0
                    }
                }
                layer.msg(response.data.msg); //弹出提示框
            })
        },
        toUpdate: function (app_id) {
            axios({
                url: '/manager/app/selectOne',
                method: 'post',
                params: {id: app_id}
            }).then(response => {
                if (response.data.code != 200) {
                    layer.msg(response.data.msg);
                    return;
                }
                layer.appVersion = response.data.data;
                console.log(layer);
                //打开layer所在的页面
                let index = layer.open({
                    type: 2,
                    title: '更新app',
                    content: '/manager/app/app-update.html',
                    area: ['60%', '80%'],
                    end: () => {
                        this.select(this.pageInfo.current, this.pageInfo.size);
                    }
                });
            });
        },
        doDelete: function (appid) {
            layer.msg('是否删除？', {
                time: 0,
                btn: ['是', '否'],
                yes: index => {
                    axios({
                        url: '/manager/app/delete',
                        params: {
                            id: appid
                        }
                    }).then(response => {
                        layer.close(index);
                        layer.msg(response.data.msg);
                        if (response.data.code == 200) {
                            this.select(this.pageInfo.current, this.pageInfo.size);
                        }
                    });
                }
            });
        }
    },

    created: function () {
        this.select(this.pageInfo.current, this.pageInfo.size);
    }
});