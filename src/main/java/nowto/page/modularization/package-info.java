/**
 * app经常有一个页面组合了多个模块的内容。
 *
 *
 * <p>针对这种页面有两种方案：
 * 1. 前端针对某个模块分别调用多个接口，在前端渲染出一个完整的页面
 * 2. 前端调用一个接口，该接口返回了整个页面所需的所有模块内容
 * </p>
 *
 * <p>该jar针对的是第二种方案，以下称为方案二。</p>
 *
 * <p>假如以json形式返回内容，方案二有两种返回格式如下：</p>
 *
 * 1.
 * <blockquote><pre>
 * {
 *     modules: {},
 *     module1 : {},
 *     module2: {},
 *     ...
 * }
 * </pre></blockquote>
 * 例如：
 * <blockquote><pre>
 * {
 *     navigationBar: {},
 *     main: {},
 *     footer:{}
 * }
 * </pre></blockquote>
 *
 * 2.
 * <blockquote><pre>modules: [
 *     {
 *         moduleName: "module1",
 *         moduleEntity: {}
 *     },
 *     {
 *         moduleName: "module2",
 *         moduleEntity: {}
 *     },
 *     ...
 * ]
 * </pre></blockquote>
 * 例如：
 * <blockquote><pre>
 * modules: [
 *     {
 *         moduleName: "navigationBar",
 *         moduleEntity: {}
 *     },
 *     {
 *         moduleName: "main",
 *         moduleEntity: {}
 *     },
 *     {
 *         moduleName: "footer",
 *         moduleEntity: {}
 *     },
 * ]
 * </pre></blockquote>
 *
 * <p>本jar采用格式2。 同时因为前端只有拿到moduleName时，才知道moduleEntiry该怎么解析， moduleEntity可以以嵌套json字符串返回。</p>
 *
 * <p>采用格式2有如下好处：</p>
 *
 * <p>
 * 模块经常变化，比如顺序调整，比如某个模块不再需要了需要删除。
 * 采用格式1，需要android、ios、h5三端都需要修改代码发布新版本。<p>
 * 采用格式2，就可以只后端改代码，三端除非有新模块的加入，那么不用改动在发布新版本。
 * 当然，也可以将模块信息保存到数据库中，使运营人员来维护。
 * </p>
 *
 * <p>
 *     使用方法：
 * </p>
 *
 * <blockquote><pre>
 * Map<String, Object> context = new HashMap();//ModuleFactory需要的条件从context中获取
 *
 * RawModuleFactory navigationBarModuleFactory = new NavigationBarModuleFactory();
 * RawModuleFactory mainBarModuleFactory = ...;
 * RawModuleFactory footerModuleFactory = ...;
 *
 * JsonModulesFactory modulesFactory = new JsonModulesFactory();
 * modulesFactory.addModuleFactory(navigationBarModuleFactory);
 * modulesFactory.addModuleFactory(mainModuleFactory);
 * modulesFactory.addModuleFactory(footerModuleFactory);
 *
 * //会在moduleEntity两端加双引号，以json字符串嵌套的方式返回，如果不需要，modulesFactory.getNormalModules(context);
 * List<? extends JsonModule>  pageModules = modulesFactory.getQuotedModules(context);
 * </pre></blockquote>
 */
package nowto.page.modularization;