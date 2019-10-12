package org.tron.core.vm2.tvm.interpretor.executors;

import org.tron.common.runtime.vm.DataWord;
import org.tron.core.vm2.tvm.ContractContext;
import org.tron.core.vm2.tvm.interpretor.Op;

public class CodeSizeOpExecutor extends OpExecutor {

  private static CodeSizeOpExecutor INSTANCE = new CodeSizeOpExecutor();

  private CodeSizeOpExecutor() {
  }

  public static CodeSizeOpExecutor getInstance() {
    return INSTANCE;
  }


  @Override
  public void exec(Op op, ContractContext context) {
    context.spendEnergy(op == Op.CODESIZE ? Op.Tier.BaseTier.asInt() : Op.Tier.ExtTier.asInt(),
        op.name());

    int length;
    if (op == Op.CODESIZE) {
      length = context.getContractBase().getCode().length;
    } else {
      DataWord address = context.stackPop();
      length = context.getCodeAt(address).length;
    }
    DataWord codeLength = new DataWord(length);

    context.stackPush(codeLength);
    context.step();

  }
}