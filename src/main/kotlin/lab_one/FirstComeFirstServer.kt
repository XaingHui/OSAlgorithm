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

    fun FCFS(works: ArrayList<Work>): Array<FirstComeFirstServer> {
        println("先来先服务")
        val Fall = arrayOf(
            FirstComeFirstServer(),
            FirstComeFirstServer(),
            FirstComeFirstServer(),
            FirstComeFirstServer(),
            FirstComeFirstServer()
        )
        for (i in 0..Fall.size - 1)
            works.add(Fall[i])
        works.forEach { it.ReadWork() }
        works.sortBy {
            it.Atime
        }
        works.forEach { it.Calculate(works) }
        return Fall
    }

}