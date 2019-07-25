#混淆等级
-optimizationpasses 5
#混淆时不使用大小写混淆类名
-dontusemixedcaseclassnames
#不忽略指定library非公共类得classes
-dontskipnonpubliclibraryclasses
#关闭优化
-dontoptimize
#忽略警告
-ignorewarnings
#关闭预校验
-dontpreverify
#混淆过程打印详细信息
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-useuniqueclassmembernames
-keepattributes Signature


-obfuscationdictionary ../dic.txt
-classobfuscationdictionary ../dic.txt
-packageobfuscationdictionary ../dic.txt


##################### glide#####################
-keep class com.bumptech.glide.Glide { *; }
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.**