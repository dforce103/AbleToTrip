# Generated by Django 5.0.4 on 2024-04-26 09:24

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('navigation', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='elevator',
            name='elevator_location',
            field=models.CharField(max_length=100, null=True),
        ),
        migrations.AlterField(
            model_name='elevator',
            name='operation_section',
            field=models.CharField(max_length=10, null=True),
        ),
        migrations.AlterField(
            model_name='lift',
            name='lift_location',
            field=models.CharField(max_length=255, null=True),
        ),
        migrations.AlterField(
            model_name='lift',
            name='operation_section',
            field=models.CharField(max_length=10, null=True),
        ),
        migrations.AlterField(
            model_name='ramp',
            name='ramp_location',
            field=models.CharField(max_length=255, null=True),
        ),
        migrations.AlterField(
            model_name='restroom',
            name='floor',
            field=models.CharField(max_length=10, null=True),
        ),
        migrations.AlterField(
            model_name='restroom',
            name='restroom_location',
            field=models.CharField(max_length=100, null=True),
        ),
    ]
