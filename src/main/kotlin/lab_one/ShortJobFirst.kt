package lab_one

import business.Work
import java.util.*

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

    fun SJF(works: ArrayList<Work>): Array<ShortJobFirst> {
        println("短作业优先")
        val Sall = arrayOf(ShortJobFirst(), ShortJobFirst(), ShortJobFirst(), ShortJobFirst(), ShortJobFirst())
        for (i in 0..Sall.size - 1)
            works.add(Sall[i])
        works.filterIsInstance<ShortJobFirst>().forEach { it.ReadWork() }
        works.sortBy { it.Stime }

        Sall.forEach { it.Calculate(works) }
        return Sall
    }
}