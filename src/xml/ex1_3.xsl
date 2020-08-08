<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2 style="text-align:center">academic_information</h2>
                <!--单重循环遍历-->
                <span style="float:left;margin:30px;">
                    <table border="1">
                        <tr bgcolor="#9acd32">
                            <th style="text-align:left">学生姓名</th>
                            <th style="text-align:left">学生编号</th>
                            <th style="text-align:left">性别</th>
                            <th style="text-align:left">出生日期</th>
                        </tr>
                        <xsl:for-each select="school/student">
                            <tr>
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="@id"/>
                                </td>
                                <td>
                                    <xsl:value-of select="sex"/>
                                </td>
                                <td>
                                    <xsl:value-of select="date"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </span>
                <!--单重循环遍历-->
                <span style="float:left;margin:30px;">
                    <table border="1">
                        <tr bgcolor="#9acd32">
                            <th style="text-align:left">课程名称</th>
                            <th style="text-align:left">课程编号</th>
                            <th style="text-align:left">学分</th>
                            <th style="text-align:left">描述</th>
                        </tr>
                        <xsl:for-each select="school/course">
                            <tr>
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="@id"/>
                                </td>
                                <td>
                                    <xsl:value-of select="credit"/>
                                </td>
                                <td>
                                    <xsl:value-of select="note"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </span>
                <span style="float:left;margin:30px;">
                    <table border="1">
                        <tr bgcolor="#9acd32">
                            <th style="text-align:left">学生编号</th>
                            <th style="text-align:left">课程编号</th>
                            <th style="text-align:left">分数</th>
                            <th style="text-align:left">成绩类型</th>
                            <th style="text-align:left">补考成绩</th>
                            <th style="text-align:left">成绩类型</th>
                            <th style="text-align:left">重修成绩</th>
                        </tr>
                        <xsl:for-each select="school/student">
                            <!--当前student只匹配子元素-->
                            <xsl:apply-templates/>
                            <!--定义学生id变量，由于学生可以选修多门课程，
                                故二重循环需一对多，在后一个tr使用-->
                            <xsl:variable name="sid" select="@id"/>
                            <xsl:for-each select="course">
                                <tr><!--引用之前定义的变量，展示tr-->
                                    <td>
                                        <xsl:value-of select="$sid"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="@id"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="score/normal"/>
                                    </td>
                                    <!--进行两种可选成绩类型的判断-->
                                    <xsl:choose>
                                        <xsl:when test="score/retake != 0">
                                            <td>retake</td><!--判断是否补考-->
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <td></td>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <td>
                                        <xsl:value-of select="score/retake"/>
                                    </td>
                                    <xsl:choose>
                                        <xsl:when test="score/rebuild != 0">
                                            <td>rebuild</td><!--判断是否重修-->
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <td></td>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <td>
                                        <xsl:value-of select="score/rebuild"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </xsl:for-each>
                    </table>
                </span>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>