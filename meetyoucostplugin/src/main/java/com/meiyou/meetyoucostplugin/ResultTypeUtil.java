package com.meiyou.meetyoucostplugin;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Author: ice
 * Date: 1/31/18 11:49.
 */

public class ResultTypeUtil {

    public static void returnResult(MethodVisitor mv, Type returnType){
        //判断是否有返回值，代码不同
        if("V".equals(returnType.getDescriptor())){
            mv.visitInsn(Opcodes.RETURN);
        }else{
            //强制转化类型
            if(!castPrimateToObj(mv, returnType.getDescriptor())){
                //这里需要注意，如果是数组类型的直接使用即可，如果非数组类型，就得去除前缀了,还有最终是没有结束符;
                //比如：Ljava/lang/String; ==》 java/lang/String
                String newTypeStr = null;
                int len = returnType.getDescriptor().length();
                if(returnType.getDescriptor().startsWith("[")){
                    newTypeStr = returnType.getDescriptor().substring(0, len);
                }else{
                    newTypeStr = returnType.getDescriptor().substring(1, len-1);
                }
                mv.visitTypeInsn(Opcodes.CHECKCAST, newTypeStr);
            }

            //这里还需要做返回类型不同返回指令也不同
            mv.visitInsn(getReturnTypeCode(returnType.getDescriptor()));
        }
    }

    /**
     * 基本类型需要做对象类型分装
     * @param mv
     * @param typeS
     * @return
     */
    private static boolean castPrimateToObj(MethodVisitor mv, String typeS){
        if("Z".equals(typeS)){

            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Boolean");//强制转化类型
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z");
            return true;
        }
        if("B".equals(typeS)){
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Byte");//强制转化类型
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B");
            return true;
        }
        if("C".equals(typeS)){
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Character");//强制转化类型
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Character", "intValue", "()C");
            return true;
        }
        if("S".equals(typeS)){
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Short");//强制转化类型
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S");
            return true;
        }
        if("I".equals(typeS)){
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Integer");//强制转化类型
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I");
            return true;
        }
        if("F".equals(typeS)){
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Float");//强制转化类型
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F");
            return true;
        }
        if("D".equals(typeS)){
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Double");//强制转化类型
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D");
            return true;
        }
        if("J".equals(typeS)){
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Long");//强制转化类型
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J");
            return true;
        }
        return false;
    }
    /**
     * 针对不同类型返回指令不一样
     * @param typeS
     * @return
     */
    private static int getReturnTypeCode(String typeS){
        if("Z".equals(typeS)){
            return Opcodes.IRETURN;
        }
        if("B".equals(typeS)){
            return Opcodes.IRETURN;
        }
        if("C".equals(typeS)){
            return Opcodes.IRETURN;
        }
        if("S".equals(typeS)){
            return Opcodes.IRETURN;
        }
        if("I".equals(typeS)){
            return Opcodes.IRETURN;
        }
        if("F".equals(typeS)){
            return Opcodes.FRETURN;
        }
        if("D".equals(typeS)){
            return Opcodes.DRETURN;
        }
        if("J".equals(typeS)){
            return Opcodes.LRETURN;
        }
        return Opcodes.ARETURN;
    }

}
