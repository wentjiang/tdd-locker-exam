## Locker Robot

### 目标：
我们要开发一个新的Locker Robot存取包系统，其中Locker/Robot/Manager可以帮助顾客存取包。
###背景：
随着互联网智能时代的快速发展，华顺超市也准备将之前的人工存取包变得更加智能化，可以让小樱(前台服务员) 一个人就可以搞定大量的存取包服务。所以特聘请你来为他们开发这个LockerRobot存取包系统。

### 业务需求如下：
华顺超市准备购买三种型号的储物柜，分别为S，M，L（S < M < L）。当顾客来存包的时候只需要将包交给小樱，之后的一系列存包会由小樱来完成。
小樱在存包之前先会拿到包裹的尺寸标签，根据不同的尺寸标签决定是直接存入Locker还是找对应Robot存包。当包裹尺寸为S时，小樱会直接存入S号的Locker中；当包裹尺寸为M时，找PrimaryLockerRobot存包；当包裹尺寸为L时，找SuperLockerRobot存包。存包成功后小樱会将票据交给顾客。存包的时候，小樱从不犯糊涂，她一定能找对目标。

当普通顾客拿着票据来取包的时候，只要把票据交给小樱，小樱会找对应的Robot或者Locker取包。
当VIP顾客来存取包时，可以直接通过VIP通道找LockerRobotManager提供专门的存取包服务。

### 业务规则
1. Locker可以存包取包
2. PrimaryLockerRobot 按照Locker顺序存，它只管理M号Locker，暂且不用考虑管理其它型号的Locker
3. SuperLockerRobot 将包存入空置率(可用容量/容量)最大的Locker，它只管理L号Locker，暂且不用考虑管理其它型号的Locker
4. 目前由于业务量比较小，LockerRobotManager只管理一个Locker（S号）、一个PrimaryLockerRobot（管理一个Locker）和SuperLockerRobot（管理一个Locker），但也不排除后期随着业务增长，LockerRobotManager会管理更多的Locker或者Robot
5. LockerRobotManager可以委派Robot存包取包，也可以自己存包取包，委派顺序没有要求
6. LockerRobotManager管理的Locker和Robot不会直接对外提供服务
7. 不同型号Locker产生的票据不通用，当用不同的型号票取包时，系统要提示票的型号不对
8. 超市管理员在配置Robot和Manager的时候，只要Locker的型号选择不对，Robot和Manager将无法正常使用


### 常见问题
1. 不存在容量为0的Locker，Robot至少要管理一个Locker
2. M，L号的Locker不对外提供服务，只能通过PrimaryLockerRobot或者SuperLockerRobot进行使用
3. 小樱会在线下对票据进行区分找不同的robot或者Locker进行取包，但她难免也有犯糊涂的时候。
4. 对于非VIP顾客找LockerRobotManager进行存取包，是线下验证还是系统验证？
5. VIP通道非VIP顾客是没法进入的。
6. LockerRobotManager管理的robot的locker可以和其他robot的locker是相同的吗？
    >不能相同，如果相同，则配置无效，将无法正常使用。
7. 小樱能区分不同类型的票据，那能够区分伪造的票据吗？
    >从实际场景出发，小樱不能够区分伪造票
8. 小樱代理用户取完包后，会回收票据吗？
    >小樱会回收，但她自己取包的时候难免也有犯糊涂的时候。

# tasking

- given 有可用容量的S型locker,需要存的S型的包 when S型locker存包 then 存包成功,返回小票
- given 有可用容量的S型locker,需要存的M型的包 when S型locker存包 then 存包失败,提示包类型错误
- given 没有可用容量的S型locker,需要存S型的包 when S型locker存包 then 存包失败,提示容量已满

- given S型locker,有效的小票 when S型locker取包 then 取包成功
- given S型locker,无效的小票 when S型locker取包 then 取包失败,提示错误的小票
- given S型locker,用过的小票 when S型locker取包 then 取包失败,提示用过的小票

- given PrimaryLockerRobot管理1个未存满的M型locker,需要存的M号包 when PrimaryLockerRobot存包 then 存包成功,返回小票
- given PrimaryLockerRobot管理2个未存满的M型locker,需要存的M号包 when PrimaryLockerRobot存包 then 返回小票,成功存包到第一个locker中
- given PrimaryLockerRobot管理2个未存满的M型locker,需要存的L号的包 when PrimaryLockerRobot存包 then 存包失败,提示包型号错误
- given PrimaryLockerRobot管理1个未存满的M型locker,需要存的M号的包 when M型locker存包 then 存包失败,提示拒绝服务

- given PrimaryLockerRobot管理2个M型locker,有效的小票 when PrimaryLockerRobot取包 then 取包成功
- given PrimaryLockerRobot管理2个M型locker,无效的小票 when PrimaryLockerRobot取包 then 取包失败,提示无效的小票
- given PrimaryLockerRobot管理2个M型locker,用过的小票 when PrimaryLockerRobot取包 then 取包失败,提示用过的小票
- given PrimaryLockerRobot管理2个M型locker,S型locker的小票 when PrimaryLockerRobot取包 then 取包失败,提示票类型错误
- given PrimaryLockerRobot管理2个M型locker,有效的小票 when M型locker取包 then 取包失败,提示拒绝服务

- given SuperLockerRobot管理1个未存满的L型locker,需要存的L号包 when SuperLockerRobot存包 then 存包成功,返回小票
- given SuperLockerRobot管理2个未存满的L型locker,第一个locker容量和可用容量分别为10,5,第二个locker容量和可用容量分别为10,3 when SuperLockerRobot存包 then 返回小票,存包到第二个locker
- given SuperLockerRobot管理2个为存满的L型的locker,需要存M号包 when SuperLockerRobot存包 then 存包失败,提示包型号错误
- given SuperLockerRobot管理1个未存满的L型locker,需要存的L号包 when L型locker存包 then 存包失败,提示拒绝服务

- given SuperLockerRobot管理2个未存满的L型locker,有效的小票 when SuperLockerRobot取包 then 取包成功
- given SuperLockerRobot管理2个未存满的L型locker,用过的小票 when SuperLockerRobot取包 then 取包失败,提示用过的小票
- given SuperLockerRobot管理2个未存满的L型locker,无效的小票 when SuperLockerRobot取包 then 取包失败,提示无效的小票
- given SuperLockerRobot管理2个未存满的L型locker,有效的小票 when L型locker取包 then 取包失败,提示拒绝服务


- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,需要存的S型的包 when LockerRobotManager存包 then 存包成功,返回小票
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,需要存的M型的包 when LockerRobotManager存包 then 存包成功,返回小票
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,需要存的L型的包 when LockerRobotManager存包 then 存包成功,返回小票
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,需要存的L型的包 when S型locker存包 then 存包失败,提示拒绝服务
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,需要存的L型的包 when PrimaryLockerRobot存包 then 存包失败,提示拒绝服务
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,需要存的L型的包 when SuperLockerRobot存包 then 存包失败,提示拒绝服务


- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,有效的S型的票据 when LockerRobotManager取包 then 取包成功
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,有效的M型的票据 when LockerRobotManager取包 then 取包成功
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,有效的L型的票据 when LockerRobotManager取包 then 取包成功
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,无效的L型的票据 when LockerRobotManager取包 then 取包失败,提示无效的小票
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,用过的L型的票据 when LockerRobotManager取包 then 取包失败,提示用过的小票
- LockerRobotManager管理一个S型locker,一个PrimaryLockerRobot管理一个locker,一个SuperLockerRobot管理一个locker,有效的S型的票据 when S型locker取包 then 取包失败,提示拒绝服务


 
