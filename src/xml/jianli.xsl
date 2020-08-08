<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- 根模板-->

    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:value-of select="简历/基本信息/姓名"/>的个人简历
                </title>
            </head>
            <body>
                <h2 align="center">简历</h2>
                <xsl:apply-templates select="简历"/>
            </body>
        </html>
    </xsl:template>

    <!--简历模板-->
    <xsl:template match="简历">
        <xsl:apply-templates select="基本信息"/>
        <br/>
        <xsl:apply-templates select="基本信息/学习阶段"/>
        <br/>
        <xsl:apply-templates select="经历信息"/>
        <br/>
        <xsl:apply-templates select="经历信息/在校经历"/>
    </xsl:template>

    <!--基本信息模板-->
    <xsl:template match="基本信息">
        <table align="center" width="800" border="0">
            <tbody>
                <tr>
                    <th colspan="7" bgcolor="#9aff32" align="left">基本信息</th>
                </tr>
                <tr>
                    <td width="35">姓名:</td>
                    <td width="50">
                        <font color="gray"><xsl:value-of select="姓名"/></font>
                    </td>
                    <td width="35">性别:</td>
                    <td width="40">
                        <font color="gray"><xsl:value-of select="性别"/></font>
                    </td>
                    <td width="35">出生日期:</td>
                    <td width="80">
                        <font color="gray"><xsl:value-of select="出生日期"/></font>
                    </td>
                    <td width="150" rowspan="2">
                        <img src="F:\IDEA\javalearning\src\javaEE\zzl2018.png" width="80" height="100px"/>
                    </td>
                </tr>
                <tr>
                    <td>家庭住址:</td>
                    <td>
                        <font color="gray"><xsl:value-of select="家庭住址"/></font>
                    </td>
                    <td>个人电话:</td>
                    <td>
                        <font color="gray"><xsl:value-of select="个人电话"/></font>
                    </td>
                    <td>联系人电话:</td>
                    <td>
                        <font color="gray"><xsl:value-of select="联系人电话"/></font>
                    </td>
                </tr>
            </tbody>
        </table>

    </xsl:template>
    <!--教育模板-->

    <!--工作模板-->
    <xsl:template match="经历信息">
        <table width="800" align="center" style="border-collapse:separate; border-spacing:0px 5px;">
            <tbody>
                <tr>
                    <th colspan="5" bgcolor="#9aff32" align="left">工作经历</th>
                </tr>
                <tr>
                    <td>公司名称</td>
                    <td>职位</td>
                    <td>开始时间</td>
                    <td>终止时间</td>
                    <td>职位描述</td>
                </tr>
                <xsl:apply-templates select="工作经历/经历"/>
            </tbody>
        </table>
    </xsl:template>
    <xsl:template match="工作经历/经历">
        <tr style="color:gray">
            <td>
                <xsl:value-of select="公司名称"/>
            </td>
            <td>
                <xsl:value-of select="职位"/>
            </td>
            <td>
                <xsl:value-of select="开始时间"/>
            </td>
            <td>
                <xsl:value-of select="终止时间"/>
            </td>
            <td width="350">
                <xsl:value-of select="职位描述"/>
            </td>
        </tr>
    </xsl:template>

    <!--在校情况-->
    <xsl:template match="经历信息/在校经历">
        <table width="800" align="center" style="border-collapse:separate; border-spacing:0px 5px;">
            <tbody>
                <tr>
                    <th colspan="5" bgcolor="#9aff32" align="left">在校情况</th>
                </tr>
                <tr>
                    <td>个人爱好</td>
                    <td>奖惩情况</td>
                    <td>科研发表文章情况</td>
                    <td>社团活动情况</td>
                </tr>
                    <xsl:apply-templates select="个人爱好"/>
                    <xsl:apply-templates select="奖惩情况"/>
                    <xsl:apply-templates select="科研发表文章情况"/>
                    <xsl:apply-templates select="社团活动情况"/>
            </tbody>
        </table>
    </xsl:template>
    <xsl:template match="个人爱好">
        <td>
            <font color="gray"><xsl:value-of select="爱好"/></font>
        </td>
    </xsl:template>
    <xsl:template match="奖惩情况">
        <td>
            <font color="gray"><xsl:value-of select="奖励"/></font>
        </td>
    </xsl:template>
    <xsl:template match="科研发表文章情况">
        <td>
            <font color="gray"><xsl:value-of select="文章/文章标题"/></font>
        </td>
    </xsl:template>
    <xsl:template match="社团活动情况">
        <td>
            <font color="gray"><xsl:value-of select="活动"/></font>
        </td>
    </xsl:template>
    <!--    <xsl:template match="个人爱好">
            <tr style="color:gray">
                <td>
                    <xsl:value-of select="经历信息/个人爱好/爱好"/>
                </td>
                <td>
                    <xsl:value-of select="经历信息/奖惩情况/奖励"/>
                </td>
                <td>
                    <xsl:value-of select="经历信息/科研发表文章情况/文章/文章标题"/>
                </td>
                <td>
                    <xsl:value-of select="经历信息/社团活动情况/活动"/>
                </td>
            </tr>
        </xsl:template>-->


    <!--自我评价模板-->
    <xsl:template match="自我评价">

        <table width="800" align="center">

            <tbody>

                <tr>

                    <th bgcolor="#CAE1FF" align="left">自我评价</th>

                </tr>

                <tr>

                    <td>
                        <xsl:value-of select="."/>
                    </td>

                </tr>

            </tbody>

        </table>

    </xsl:template>
</xsl:stylesheet>