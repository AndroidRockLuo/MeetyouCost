package com.meiyou.meetyoucostplugin;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.signature.SignatureVisitor;

/**
 * 方法耗时 visitor
 * Author: lwh
 * Date: 4/28/17 15:37.
 */

public class CostMethodClassVisitor extends ClassVisitor {

    private String className;
    public CostMethodClassVisitor(String className,ClassVisitor classVisitor) {
        super(Opcodes.ASM5,classVisitor);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature,
                                     String[] exceptions) {

        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        methodVisitor = new AdviceAdapter(Opcodes.ASM5, methodVisitor, access, name, desc) {

            boolean inject = false;
            private boolean isInject(){
               /* if(name.equals("setStartTime") || name.equals("setEndTime") || name.equals("getCostTime")){
                   return false;
                }
                return true;*/
               return inject;
            }
            @Override
            public void visitCode() {
                super.visitCode();

            }

            @Override
            public org.objectweb.asm.AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                if (Type.getDescriptor(Cost.class).equals(desc)) {
                    inject = true;

                }

                return super.visitAnnotation(desc, visible);
            }

            @Override
            public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                super.visitFieldInsn(opcode, owner, name, desc);
            }

            public void print(String msg){
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn(msg);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                        "(Ljava/lang/String;)V", false);
            }

            @Override
            protected void onMethodEnter() {
                //super.onMethodEnter();
                //统计public static类方法
               /* if(access==Opcodes.ACC_STATIC+Opcodes.ACC_PUBLIC
                        && !name.equals("countStaticClass")
                        && !name.equals("isOk")
                        && !className.equals("com/meiyou/meetyoucost/CostLog")){
                    StringBuilder sb = new StringBuilder();
                    sb.append("Usopp MeetyouCost Statics :").append(className).append(":").append(name);
                    //String log = className+":contains public static method:"+name+":"+desc;
                    //TestController:contains public static method:getInstance:()Lcom/meiyou/meetyoucostdemo/TestController;
                    mv.visitLdcInsn(className);
                    mv.visitLdcInsn(name);
                    String log = sb.toString();
                    mv.visitLdcInsn(log);
                    mv.visitMethodInsn(INVOKESTATIC, "com/meiyou/meetyoucost/CostLog", "countStaticClass",
                            "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
                    // mw.visitInsn(RETURN);
                    // 这段代码使用最多一个栈元素和一个本地变量
                    //mw.visitMaxs(1, 1);
                }*/
                //统计方法耗时
                if(isInject()){
                    if(name.equals("isOk")){
                       /* mv.visitMethodInsn(INVOKESTATIC, "com/meiyou/meetyoucost/CostLog", "isOk",
                                "()Z", false);
                        Type type = Type.getReturnType(desc);
                        ResultTypeUtil.returnResult(mv,type);*/
                       //理解这篇文章
                       //https://www.cnblogs.com/coding-way/p/6600647.html
                        mv.visitLdcInsn(false);
                        mv.visitInsn(IRETURN);
                    }else{
                        mv.visitLdcInsn(className+":"+name+desc);
                        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
                        mv.visitMethodInsn(INVOKESTATIC, "com/meiyou/meetyoucost/CostLog", "setStartTime",
                                "(Ljava/lang/String;J)V", false);
                    }
                }
            }

            @Override
            protected void onMethodExit(int i) {
                //super.onMethodExit(i);
                if(isInject()){
                    mv.visitLdcInsn(className+":"+name+desc);
                    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/meiyou/meetyoucost/CostLog", "setEndTime",
                            "(Ljava/lang/String;J)V", false);

                    /*mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    mv.visitLdcInsn(className+":"+name+desc);
                    mv.visitMethodInsn(INVOKESTATIC, "com/meiyou/meetyoucost/CostLog", "getCostTime",
                            "(Ljava/lang/String;)Ljava/lang/String;", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                            "(Ljava/lang/String;)V", false);*/

                    //mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    //mv.visitLdcInsn("========end=========");
                    //mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                    //      "(Ljava/lang/String;)V", false);
                }
            }
        };
        return methodVisitor;
        //return super.visitMethod(i, s, s1, s2, strings);

    }
}
