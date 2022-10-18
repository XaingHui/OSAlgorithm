package lab_one

import business.Work
import sun.nio.cs.ext.SJIS
import java.util.*
import kotlin.collections.ArrayList

class ShortJobFirst(override var Pname: String, override var Atime: Int, override var Stime: Int) : Work {
    override var Ftime: Int = 1
    override var Rtime: Int = 1
    override var DRtime: Double = 1.0
    override var AverRtime: Double = 1.0
    override var AveDRtime: Double = 1.0

    override fun ReadWork(): Work {
        val read = Scanner(System.`in`)
        println("请输入")
        println("作业名\t到达时间\t服务时间\t 并以回车结束")
        Pname = read.next()
        Atime = read.nextInt()
        Stime = read.nextInt()
        return (ShortJobFirst(Pname, Atime, Stime))
    }

    constructor() : this("", 0, 0)

    companion object {
        fun SJF(works: ArrayList<Work>, N: Int = 1): ArrayList<Work> {
            println("短作业优先")
            for (i in 0..N - 1)
                works.add(ShortJobFirst())
            works.forEach { it.ReadWork() }
            val SJFs = works.sortedBy { it.Stime }
            val SJFss = ArrayList<Work>()
            SJFs.forEach { SJFss.add(it) }
            works.forEach { it.Calculate(works) }
            return SJFss
        }
    }
}