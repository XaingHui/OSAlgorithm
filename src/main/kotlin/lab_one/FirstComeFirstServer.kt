package lab_one

import business.Calculate
import business.Work
import java.util.*
import kotlin.collections.ArrayList

class FirstComeFirstServer(
    override var Pname: String, override var Atime: Int,
    override var Stime: Int,
) : Work, Calculate {

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
        return (FirstComeFirstServer(Pname, Atime, Stime))
    }

    constructor() : this("", 0, 0)

    companion object {
        fun FCFS(works: ArrayList<Work>, N: Int = 2): ArrayList<Work> {
            println("先来先服务")
            for (i in 0..N - 1)
                works.add(FirstComeFirstServer())

            works.forEach { it.ReadWork() }
            val Falls = works.sortedBy {
                it.Atime
            }
            val Fallss = ArrayList<Work>()
            Falls.forEach { Fallss.add(it) }
            works.forEach { it.Calculate(works) }
            return Fallss
        }
    }

}