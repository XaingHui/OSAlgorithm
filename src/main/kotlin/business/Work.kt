package business

interface Work : Calculate {
     var Pname: String
     var Atime: Int
     var Stime: Int
     var Ftime: Int
     var Rtime: Int
     var DRtime: Double

     var AverRtime: Double
     var AveDRtime: Double

     fun ReadWork(): Work
     fun PrintWork() {
          print("${Pname}\t\t")
          print("${Atime}\t\t")
          print("${Stime}\t\t")
          print("${Ftime}\t\t")
          print("${Rtime}\t\t")
          print("${DRtime}\t\t")
          println()
     }
}
