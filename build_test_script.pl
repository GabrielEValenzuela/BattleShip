#!/usr/bin/perl

use warnings;
use strict;
use feature 'state';

sub read_coverage{
    state $porcentage = 0;
    if($_[0] =~ /^coverage/ ){
        ($porcentage) = $_[0] =~ /(\d+)/;
    }
    return $porcentage;
}

sub read_pmd_status{
    state $pmd_count = 0;
    if($_[0] =~ /^pmd/ ){
        ($pmd_count) = $_[0] =~ /(\d+)/;
    }
    return $pmd_count;
}

sub read_cs_status{
    state $cs_count = 0;
    if($_[0] =~ /^checkstyle/ ){
        ($cs_count) = $_[0] =~ /(\d+)/;
    }
    return $cs_count;
}

sub read_coverage_old{
    state $porcentage_old = 0;
    if($_[0] =~ /previous_coverage/ ){
        ($porcentage_old) = $_[0] =~ /(\d+)/;
    }
    return $porcentage_old;
}

sub read_pmd_status_old{
    state $pmd_count_old = 0;
    if($_[0] =~ /previous_pmd/ ){
        ($pmd_count_old) = $_[0] =~ /(\d+)/;
    }
    return $pmd_count_old;
}

sub read_cs_status_old{
    state $cs_count_old = 0;
    if($_[0] =~ /previous_checkstyle/ ){
        ($cs_count_old) = $_[0] =~ /(\d+)/;
    }
    return $cs_count_old;
}

=for comment
    $_[0] -> coverage new
    $_[1] -> pmd new
    $_[2] -> cs new

    $_[3] -> coverage old
    $_[4] -> pmd old
    $_[5] -> cs old
=cut
sub continue_build{
    if($_[0] < $_[3] || $_[1] > $_[4] || $_[2] > $_[5]){
        return -1;
    }
    return 0;
}

my $file_stats = 'stats.txt';

my $coverage_old = 0;
my $pmd_old = 0;
my $checkstyle_old = 0;

my $coverage_new = 0;
my $pmd_new = 0;
my $checkstyle_new = 0;

open(my $file_handler,'<',$file_stats) or die $!;

while(<$file_handler>){
    $coverage_new   =  read_coverage($_);
    $pmd_new        =  read_pmd_status($_);
    $checkstyle_new =  read_cs_status($_);
    $coverage_old   =  read_coverage_old($_);
    $pmd_old        =  read_pmd_status_old($_);
    $checkstyle_old =  read_cs_status_old($_);
}

=for comment
    print("Data read\n");
    print("$coverage_new  \n");
    print("$pmd_new       \n");
    print("$checkstyle_new\n");
    print("$coverage_old  \n");
    print("$pmd_old       \n");
    print("$checkstyle_old\n");
    print("===============\n");
=cut

my $flag = continue_build($coverage_new,$pmd_new,$checkstyle_new,$coverage_old,$pmd_old,$checkstyle_old);
print("$flag\n");

close($file_handler);