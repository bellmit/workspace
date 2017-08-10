import {
  Enum
} from 'enumify';

class CellType extends Enum {}
/**
 * TEXT: 文本
 * NUMBER：数字
 * RATE：Rate 评分组件
 * PERCENTAGE：%组件
 */
CellType.initEnum(['TEXT', 'NUMBER', 'RATE', 'PERCENTAGE']);


// LogisticsTransport begin
let LogisticsTransportItemTypes = [{
    id: "all",
    name: "全部"
  },
  {
    id: "231",
    name: "公路"
  },
  {
    id: "232",
    name: "铁路"
  },
  {
    id: "233",
    name: "内河航运"
  },
  {
    id: "234",
    name: "海运"
  },
  {
    id: "237",
    name: "空运"
  }
];
let LogisticsTransportColumns = [{
    columnName: "物流商名称",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-150px": true
    }
  },
  {
    columnName: "等级",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true
    }
  },
  {
    columnName: "运力",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-4": true
    }
  },
  {
    columnName: "订单数",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-4": true
    }
  },
  {
    columnName: "金额(万元)",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-4": true,
      "up-icon": true
    }
  }
]

function processLogisticsTransport(dataList) {
  if (!dataList || dataList.length === 0) return

  let totalRow = dataList.pop()
  let t1 = +totalRow["运力"]
  let t2 = +totalRow["订单数"]
  let t3 = +totalRow["金额(万元)"]

  let dataRows = dataList.map(row => {

    return [
      new Cell("物流商名称", row["物流商名称"], CellType.TEXT, {
        objClass: {
          'cell-valign-middle': true,
          'cell-150px': true
        }
      }),
      new Cell("等级", +row["等级"], CellType.RATE, {
        objClass: {
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("运力", +row["运力"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["运力"], t1),
        objClass: {
          'cell-1-4': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-1': true
        }
      }),
      new Cell("订单数", +row["订单数"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["订单数"], t2),
        objClass: {
          'cell-1-4': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-2': true
        }
      }),
      new Cell("金额(万元)", row["金额(万元)"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["金额(万元)"], t3),
        objClass: {
          'cell-1-4': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-3': true
        }
      })
    ]
  })

  return {
    dataRows,
    totalRow: [
      new Cell("物流商名称", totalRow["物流商名称"], CellType.TEXT, {
        objClass: {
          'cell-valign-middle': true,
          'cell-150px': true
        }
      }),
      new Cell("等级", "", CellType.TEXT, {
        objClass: {
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("运力", +totalRow["运力"], CellType.NUMBER, {
        objClass: {
          "cell-1-4": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("订单数", +totalRow["订单数"], CellType.NUMBER, {
        objClass: {
          "cell-1-4": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("金额(万元)", totalRow["金额(万元)"], CellType.NUMBER, {
        objClass: {
          "cell-1-4": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      })
    ]
  }
}

export let LogisticsTransportService = {
  dataTypes: LogisticsTransportItemTypes,
  columns: LogisticsTransportColumns,
  process: processLogisticsTransport
}

// LogisticsTransport end

// 物流方式分析 begin
let ShippingItemTypes = [
  // {
  //   id: "all",
  //   name: "全部"
  // },
  {
    id: "231",
    name: "公路"
  },
  {
    id: "232",
    name: "铁路"
  },
  {
    id: "233",
    name: "内河航运"
  },
  {
    id: "234",
    name: "海运"
  },
  {
    id: "237",
    name: "空运"
  }
];
let ShippingColumns = [{
    columnName: "发运方向",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      // "cell-150px": true
    }
  },
  // {
  //   columnName: "等级",
  //   objClass: {
  //     "cell-valign-middle": true,
  //     "cell-align-center": true
  //   }
  // },
  {
    columnName: "产品类型(个)",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-5": true
    }
  },
  {
    columnName: "重量(T)",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-5": true
    }
  },
  {
    columnName: "体积(m^2)",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-5": true
    }
  },
  {
    columnName: "金额(万元)",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-5": true,
      "up-icon": true
    }
  }
]

function processShipping(dataList) {
  if (!dataList || dataList.length === 0) return

  let totalRow = dataList.pop()
  let t1 = +totalRow["产品类型(个)"]
  let t2 = +totalRow["重量(T)"]
  let t3 = +totalRow["体积(m^2)"]
  let t4 = +totalRow["金额(万元)"]

  let dataRows = dataList.map(row => {

    return [
      new Cell("发送方向", row["发送方向"], CellType.TEXT, {
        objClass: {
          'cell-valign-middle': true,
          //'cell-150px': true
        }
      }),
      new Cell("产品类型(个)", +row["产品类型(个)"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["产品类型(个)"], t1),
        objClass: {
          'cell-1-5': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-1': true
        }
      }),
      new Cell("重量(T)", +row["重量(T)"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["重量(T)"], t2),
        objClass: {
          'cell-1-5': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-1': true
        }
      }),
      new Cell("体积(m^2)", +row["体积(m^2)"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["体积(m^2)"], t3),
        objClass: {
          'cell-1-5': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-2': true
        }
      }),
      new Cell("金额(万元)", row["金额(万元)"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["金额(万元)"], t4),
        objClass: {
          'cell-1-5': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-3': true
        }
      })
    ]
  })

  return {
    dataRows,
    totalRow: [
      new Cell("发送方向", totalRow["发送方向"], CellType.TEXT, {
        objClass: {
          'cell-valign-middle': true,
          //'cell-150px': true
        }
      }),
      // new Cell("等级", "", CellType.TEXT, {
      //   objClass: {
      //     'cell-valign-middle': true,
      //     'cell-align-center': true
      //   }
      // }),
      new Cell("产品类型(个)", +totalRow["产品类型(个)"], CellType.NUMBER, {
        objClass: {
          "cell-1-5": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("重量(T)", +totalRow["重量(T)"], CellType.NUMBER, {
        objClass: {
          "cell-1-5": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("体积(m^2)", totalRow["体积(m^2)"], CellType.NUMBER, {
        objClass: {
          "cell-1-5": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("金额(万元)", totalRow["金额(万元)"], CellType.NUMBER, {
        objClass: {
          "cell-1-5": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      })
    ]
  }
}

export let ShippingService = {
  dataTypes: ShippingItemTypes,
  columns: ShippingColumns,
  process: processShipping
}

// 物流方式分析 end

// Warehousing begin
let WarehousingItemTypes = [{
    id: "all",
    name: "全部"
  },
  {
    id: "308",
    name: "普通"
  },
  {
    id: "309",
    name: "恒温"
  },
  {
    id: "310",
    name: "冷库"
  },
  {
    id: "311",
    name: "危险"
  },
  {
    id: "365",
    name: "保税"
  },
  {
    id: "366",
    name: "海关监管"
  },
  {
    id: "367",
    name: "其它"
  }
];
let WarehousingColumns = [{
    columnName: "仓库",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      //"cell-150px": true
    }
  },
  // {
  //   columnName: "等级",
  //   objClass: {
  //     "cell-valign-middle": true,
  //     "cell-align-center": true
  //   }
  // },
  {
    columnName: "仓储数",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-4": true
    }
  },
  {
    columnName: "订单数",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-4": true
    }
  },
  {
    columnName: "人民币(万元)",
    objClass: {
      "cell-valign-middle": true,
      "cell-align-center": true,
      "cell-1-4": true,
      "up-icon": true
    }
  }
]

function processWarehousing(dataList) {
  if (!dataList || dataList.length === 0) return

  let totalRow = dataList.pop()
  let t1 = +totalRow["仓储数"]
  let t2 = +totalRow["订单数"]
  let t3 = +totalRow["人民币(万元)"]

  let dataRows = dataList.map(row => {

    return [
      new Cell("仓库", row["仓库"], CellType.TEXT, {
        objClass: {
          'cell-valign-middle': true,
          //'cell-150px': true
        }
      }),
      // new Cell("等级", +row["等级"], CellType.RATE, {
      //   objClass: {
      //     'cell-valign-middle': true,
      //     'cell-align-center': true
      //   }
      // }),
      new Cell("仓储数", +row["仓储数"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["仓储数"], t1),
        objClass: {
          'cell-1-4': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-1': true
        }
      }),
      new Cell("订单数", +row["订单数"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["订单数"], t2),
        objClass: {
          'cell-1-4': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-2': true
        }
      }),
      new Cell("人民币(万元)", row["人民币(万元)"], CellType.PERCENTAGE, {
        percentage: calcPercentage(+row["人民币(万元)"], t3),
        objClass: {
          'cell-1-4': true,
          'cell-valign-middle': true
        },
        barBG: {
          'color-3': true
        }
      })
    ]
  })

  return {
    dataRows,
    totalRow: [
      new Cell("仓库", totalRow["仓库"], CellType.TEXT, {
        objClass: {
          'cell-valign-middle': true,
          //'cell-150px': true
        }
      }),
      // new Cell("等级", "", CellType.TEXT, {
      //   objClass: {
      //     'cell-valign-middle': true,
      //     'cell-align-center': true
      //   }
      // }),
      new Cell("仓储数", +totalRow["仓储数"], CellType.NUMBER, {
        objClass: {
          "cell-1-4": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("订单数", +totalRow["订单数"], CellType.NUMBER, {
        objClass: {
          "cell-1-4": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      }),
      new Cell("人民币(万元)", totalRow["人民币(万元)"], CellType.NUMBER, {
        objClass: {
          "cell-1-4": true,
          'cell-valign-middle': true,
          'cell-align-center': true
        }
      })
    ]
  }
}

export let WarehousingService = {
  dataTypes: WarehousingItemTypes,
  columns: WarehousingColumns,
  process: processWarehousing
}

// Warehousing end


function calcPercentage(numerator, denominator, decimals = 2) {
  if (denominator) {
    return ((numerator / denominator) * 100).toFixed(decimals)
  }

  return 0
}

class Cell {
  constructor(columnName, columnValue, cellType, tag) {
    this.columnName = columnName;
    this.columnValue = columnValue;
    this.tag = tag
    this.cellType = cellType
  }
}
