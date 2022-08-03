const base = {
    get() {
        return {
            url : "http://localhost:8080/ssmgf60m/",
            name: "ssmgf60m",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/ssmgf60m/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于协同过滤算法的超市自动销售系统"
        } 
    }
}
export default base
