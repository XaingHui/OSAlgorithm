package business

import com.sun.jmx.remote.internal.ArrayQueue
import lab_one.FirstComeFirstServer
import java.util.*
import kotlin.collections.ArrayList

interface Work : Calculate {
    var pname: String
    var atime: Int
    var stime: Int
    var ftime: Int
    var rtime: Int
    var drtime: Double

    var aver_rtime: Double
    var aver_drtime: Double


    var priority: Int
    fun ReadWork(): Work {
        val read = Scanner(System.`in`)
        println("请输入")
        println("作业名\t到达时间\t服务时间\t 并以回车结束")
        pname = read.next()
        atime = read.nextInt()
        stime = read.nextInt()
        return FirstComeFirstServer(pname, atime, stime)
    }

    fun PrintWork() {
        print("${pname}\t\t")
        print("${atime}\t\t")
        print("${stime}\t\t")
        print("${ftime}\t\t")
        print("${rtime}\t\t")
        print("${drtime}\t\t")
        println()
    }

}
